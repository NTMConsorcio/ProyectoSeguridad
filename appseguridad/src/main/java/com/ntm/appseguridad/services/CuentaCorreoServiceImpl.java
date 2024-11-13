package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.CuentaCorreo;
import com.ntm.appseguridad.entities.Provincia;
import com.ntm.appseguridad.mappers.CuentaCorreoMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.CuentaCorreoRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class CuentaCorreoServiceImpl extends BaseServiceImpl<CuentaCorreo,String> implements CuentaCorreoService {

    private final CuentaCorreoRepository cuentacorreoRepository;
    private final CuentaCorreoMapper cuentaCorreoMapper;

    public CuentaCorreoServiceImpl(BaseRepository<CuentaCorreo, String> baserepository, CuentaCorreoRepository cuentacorreoRepository, CuentaCorreoMapper cuentaCorreoMapper) {super(baserepository);
        this.cuentacorreoRepository = cuentacorreoRepository;
        this.cuentaCorreoMapper = cuentaCorreoMapper;
    }

    @Override
    public <D> D convertToDto(CuentaCorreo entity) {
        return (D) cuentaCorreoMapper.toDTO(entity);
    }

    @Override
    public <D> List<D> convertToDtoList(List<CuentaCorreo> entities) {
        return (List<D>) cuentaCorreoMapper.toDtoList(entities);
    }

    @Override
    public boolean validar(CuentaCorreo entity, String caso) throws ErrorServiceException {
        try {
            if (entity.getCorreo() == null) {
                throw new ErrorServiceException("Debe indicar el correo");
            }
            if (caso.equals("SAVE")) {
                if (cuentacorreoRepository.existsByCorreoAndEliminadoFalse(entity.getCorreo())) {
                    throw new ErrorServiceException("El correo ya existe en el sistema");
                }
            } else {
                CuentaCorreo cc = cuentacorreoRepository.findByCorreoAndEliminadoFalse(entity.getCorreo());
                if (cc != null) {
                    if (!cc.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El email especificado ya existe en el sistema");
                    }
                }
            }

            if (entity.getSmtp() == null || entity.getSmtp().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el smtp");
            }
            if (entity.getClave() == null || entity.getClave().isEmpty()) {
                throw new ErrorServiceException("Debe indicar la clave");
            }
            if (entity.getPuerto() == null || entity.getPuerto().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el puerto");
            }
            if (entity.getEmpresa() != null) {
                throw new ErrorServiceException("Debe indicar la empresa");
            }
            return true;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex ) {
            throw new ErrorServiceException("Error de sistemas");
        }

    }


    public void sendEmail(String destino, String asunto, String cuerpo) throws ErrorServiceException {
        try {
            //recupera la cuenta de correo del consorcio
            CuentaCorreo cuentaCorreo = cuentacorreoRepository.findByCorreoAndEliminadoFalse(destino);
            //Recuperamos la direccion origen
            final String direccionDesde = cuentaCorreo.getCorreo();
            //Recuperamos la clave
            final String contrasenia = cuentaCorreo.getClave();

            //Se crea un properties que almacena la configuracion (almacenada en cuentaCorreo)
            Properties propiedades = new Properties();
            propiedades.put("mail.smtp.host", cuentaCorreo.getSmtp());
            propiedades.put("mail.smtp.port", cuentaCorreo.getPuerto());
            propiedades.put("mail.smtp.auth", "true");
            propiedades.put("mail.smtp.starttls.enable", cuentaCorreo.isTls());

            //Crea una sesión del correo con autenticación
            Session session = Session.getInstance(propiedades, new Authenticator() {
                //Obtener credenciales de autenticación
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(direccionDesde, contrasenia);
                }
            });

            // Creamos el mensaje
            Message mensaje = new MimeMessage(session);
            // Seteamos la dirección de origen
            mensaje.setFrom(new InternetAddress(direccionDesde));
            // Seteamos la dirección de destino
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
            // Seteamos el asunto
            mensaje.setSubject(asunto);

            // Creamos el cuerpo del mensaje o correo
            MimeBodyPart cuerpoMensaje = new MimeBodyPart();
            // Seteamos el cuerpo en el MimeBodyPart
            cuerpoMensaje.setText(cuerpo);



            // Se combinan las partes en una
            Multipart multipart = new MimeMultipart();
            //Agregamos el cuerpo del mensaje
            multipart.addBodyPart(cuerpoMensaje);

            //Agregamos el multipart al mensaje
            mensaje.setContent(multipart);

            // Enviamos el correo
            Transport.send(mensaje);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new ErrorServiceException("Error generando el mail");
        }
    }
}
