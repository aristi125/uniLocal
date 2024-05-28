package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.servicios.interfaces.ImagenesServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImagenesServicioImpl implements ImagenesServicio {

    private final Cloudinary cloudinary;

    public ImagenesServicioImpl() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dyr9bwc7g");
        config.put("api_key", "321999241656739");
        config.put("api_secret", "BsPSNAhb_ZJIpZJd0WKJPYHvt-A");

        cloudinary = new Cloudinary(config);
    }

    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {
        File file = convertir(imagen);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "unilocal"));
    }

    @Override
    public Map eliminarImagen(String idImagen) throws Exception {
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }

    private File convertir(MultipartFile imagen) throws Exception {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}
