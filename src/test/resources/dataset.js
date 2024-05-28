db = connect( 'mongodb://root:example@localhost:27017/UniLocal?authSource=admin' );
db.clientes.insertMany([
    {
        _id: 'Cliente1',
        nickname: 'juanito',
        ciudad: 'ARMENIA',
        fotoPerfil: 'mi foto',
        email: 'juan@email.com',
        password: 'mipassword',
        nombre: 'Juan',
        estado: 'ACTIVO',
        agregarFavoritos: ['Negocio3', 'Negocio4'],
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente2',
        nickname: 'maria',
        ciudad: 'ARMENIA',
        fotoPerfil: 'mi foto',
        email: 'maria@email.com',
        password: 'mipassword',
        nombre: 'Maria',
        estado: 'ACTIVO',
        agregarFavoritos: ['Negocio3', 'Negocio4'],
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente3',
        nickname: 'pedrito',
        ciudad: 'ARMENIA',
        fotoPerfil: 'mi foto',
        email: 'pedro@email.com',
        password: 'mipassword',
        nombre: 'Pedro',
        estado: 'ACTIVO',
        agregarFavoritos: ['Negocio3', 'Negocio4'],
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente4',
        nickname: 'aleja',
        ciudad: 'ARMENIA',
        fotoPerfil: 'mi foto',
        email: 'aleja@gmail.com',
        password: 'mipassword',
        nombre: 'Aleja',
        estado: 'ACTIVO',
        agregarFavoritos: ['Negocio3', 'Negocio4'],
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente5',
        nickname: 'anita',
        ciudad: 'ARMENIA',
        fotoPerfil: 'mi foto',
        email: 'ana@gmail.com',
        password: 'mipassword',
        nombre: 'Ana',
        estado: 'ACTIVO',
        agregarFavoritos: ['Negocio3', 'Negocio4'],
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Cliente'
    }
]);

db.moderadores.insertMany([
    {
        _id: 'Moderador1',
        email: 'moderador@email.com',
        nombre: 'moderador',
        password: 'mipassword',
        estado: 'ACTIVO',
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Moderador'
    },
    {
        _id: 'Moderador2',
        email: 'moderador2@email.com',
        nombre: 'moderador2',
        password: 'mipassword',
        estado: 'ACTIVO',
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Moderador'
    },
    {
        _id: 'Moderador3',
        email: 'moderador3@gmail.com',
        nombre: 'moderador3',
        password: 'mipassword',
        estado: 'ACTIVO',
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Moderador'
    },
    {
        _id: 'Moderador4',
        email: 'moderador4@email.com',
        nombre: 'moderador4',
        password: 'mipassword',
        estado: 'ACTIVO',
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Moderador'
    },
    {
        _id: 'Moderador5',
        email: 'moderador5@email.com',
        nombre: 'moderador5',
        password: 'mipassword',
        estado: 'ACTIVO',
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Moderador'
    }
]);

db.negocios.insertMany([
    {
        _id: 'Negocio1',
        nombre: 'Restaurante Mexicano',
        descripcion: 'Restaurante de comida mexicana en Armenia',
        categoriaNegocio: 'RESTAURANTE',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        imagenes: ['imagen1', 'imagen2'],
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: new Date(2024, 3, 18, 15, 30, 45),
                horaFin: new Date(2024, 3, 18, 15, 30, 45),
            }
        ],
        telefonos: ['1234567', '7654321'],
        comentarios: [{
            _id: 'Comentario1',
            fecha: new Date(2024, 3, 18, 15, 30, 45),
            calificacion: 5,
            codigoCliente: 'Cliente1',
            mensaje: "Excelente sitio, muy buena atención",
            respuesta: ""
        }],
        calificaciones: 4,
        historialRevisiones: [{
            descripcion: 'Se realizó una revisión de la información del negocio',
            estado: 'APROBADO',
            fecha: new Date('2024-04-18'),
            codigoModerador: 'Moderador1',
            codigoNegocio: 'Negocio1'

        }],
        codigoCliente: 'Cliente1',
        estado: 'ACTIVO',
        listaReservas: [{
            fecha: new Date('2024-04-18'),
            horaInicio: new Date(2024, 3, 18, 15, 30, 45),
            cantidadPersonas: 5,
            codigoCliente: 'Cliente1',
            codigoNegocio: 'Negocio1'
        }],
        agenda: {
            tematica: 'Mexicana',
            descripcion: 'Fiesta mexicana',
        },
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Negocio'
    },
    {
        _id: 'Negocio2',
        nombre: 'Hotel premium',
        descripcion: 'Hotel de lujo en Armenia',
        categoriaNegocio: 'HOTEL',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        imagenes: ['imagen1', 'imagen2'],
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: new Date(2024, 3, 18, 15, 30, 45),
                horaFin: new Date(2024, 3, 18, 15, 30, 45)
            }
        ],
        telefonos: ['1234567', '7654321'],
        comentarios: [{
            _id: 'Comentario1',
            fecha: new Date(2024, 3, 18, 15, 30, 45),
            calificacion: 5,
            codigoCliente: 'Cliente2',
            mensaje: "Excelente sitio, muy buena atención",
            respuesta: ""
        }],
        calificaciones: 4,
        historialRevisiones: [{
            descripcion: 'Se realizó una revisión de la información del negocio',
            estado: 'APROBADO',
            fecha: new Date('2024-04-18'),
            codigoModerador: 'Moderador1',
            codigoNegocio: 'Negocio2'

        }],
        codigoCliente: 'Cliente2',
        estado: 'ACTIVO',
        listaReservas: [{
            fecha: new Date('2024-04-18'),
            horaInicio:new Date(2024, 3, 18, 15, 30, 45),
            cantidadPersonas: 5,
            codigoCliente: 'Cliente1',
            codigoNegocio: 'Negocio1'
        }],
        agenda: {
            tematica: 'Descanso',
            descripcion: 'Relajación',
        },
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Negocio'
    },{
        _id: 'Negocio3',
        nombre: 'CAFE',
        descripcion: 'Cafeteria en Armenia',
        categoriaNegocio: 'CAFETERIA',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        imagenes: ['imagen1', 'imagen2'],
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: new Date(2024, 3, 18, 15, 30, 45),
                horaFin: new Date(2024, 3, 18, 15, 30, 45)
            }
        ],
        telefonos: ['1234567', '7654321'],
        comentarios: [{
            _id: 'Comentario1',
            fecha: new Date(2024, 3, 18, 15, 30, 45),
            calificacion: 5,
            codigoCliente: 'Cliente3',
            mensaje: "Excelente sitio, muy buena atención",
            respuesta: ""
        }],
        calificaciones: 4,
        historialRevisiones: [{
            descripcion: 'Se realizó una revisión de la información del negocio',
            estado: 'APROBADO',
            fecha: new Date('2024-04-18'),
            codigoModerador: 'Moderador1',
            codigoNegocio: 'Negocio3'

        }],
        codigoCliente: 'Cliente3',
        estado: 'ACTIVO',
        listaReservas: [{
            fecha: new Date('2024-04-18'),
            horaInicio: new Date(2024, 3, 18, 15, 30, 45),
            cantidadPersonas: 5,
            codigoCliente: 'Cliente1',
            codigoNegocio: 'Negocio3'
        }],
        agenda: {
            tematica: 'Cafeteria',
            descripcion: 'Cafeteria',
        },
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Negocio'
    },{
        _id: 'Negocio4',
        nombre: 'Museo de Arte',
        descripcion: 'Museo de arte en Armenia',
        categoriaNegocio: 'MUSEO',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        imagenes: ['imagen1', 'imagen2'],
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: new Date(2024, 3, 18, 15, 30, 45),
                horaFin: new Date(2024, 3, 18, 15, 30, 45)
            }
        ],
        telefonos: ['1234567', '7654321'],
        comentarios: [{
            _id: 'Comentario1',
            fecha: new Date(2024, 3, 18, 15, 30, 45),
            calificacion: 5,
            codigoCliente: 'Cliente4',
            mensaje: "Excelente sitio, muy buena atención",
            respuesta: ""
        }],
        calificaciones: 4,
        historialRevisiones: [{
            descripcion: 'Se realizó una revisión de la información del negocio',
            estado: 'APROBADO',
            fecha: new Date('2024-04-18'),
            codigoModerador: 'Moderador1',
            codigoNegocio: 'Negocio4'

        }],
        codigoCliente: 'Cliente4',
        estado: 'ACTIVO',
        listaReservas: [{
            fecha: new Date('2024-04-18'),
            horaInicio: new Date(2024, 3, 18, 15, 30, 45),
            cantidadPersonas: 5,
            codigoCliente: 'Cliente1',
            codigoNegocio: 'Negocio4'
        }],
        agenda: {
            tematica: 'Arte',
            descripcion: 'Arte',
        },
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Negocio'
    },{
        _id: 'Negocio5',
        nombre: 'super perros calientes',
        descripcion: 'Perros calientes en Armenia',
        categoriaNegocio: 'COMIDA_RAPIDA',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        imagenes: ['imagen1', 'imagen2'],
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: new Date(2024, 3, 18, 15, 30, 45),
                horaFin: new Date(2024, 3, 18, 15, 30, 45)
            }
        ],
        telefonos: ['1234567', '7654321'],
        comentarios: [{
            _id: 'Comentario1',
            fecha: new Date(2024, 3, 18, 15, 30, 45),
            calificacion: 5,
            codigoCliente: 'Cliente5',
            mensaje: "Excelente sitio, muy buena atención",
            respuesta: ""
        }],
        calificaciones: 4,
        historialRevisiones: [{
            descripcion: 'Se realizó una revisión de la información del negocio',
            estado: 'APROBADO',
            fecha: new Date('2024-04-18'),
            codigoModerador: 'Moderador1',
            codigoNegocio: 'Negocio5'

        }],
        codigoCliente: 'Cliente5',
        estado: 'ACTIVO',
        listaReservas: [{
            fecha: new Date('2024-04-18'),
            horaInicio: new Date(2024, 3, 18, 15, 30, 45),
            cantidadPersonas: 5,
            codigoCliente: 'Cliente1',
            codigoNegocio: 'Negocio5'
        }],
        agenda: {
            tematica: 'Comida Rapida',
            descripcion: 'Comida Rapida',
        },
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Negocio'
    }


]);

db.comentarios.insertMany([
    {
        _id: 'Comentario1',
        fecha: new Date(2024, 3, 18, 15, 30, 45),
        calificacion: 5,
        codigoCliente: 'Cliente1',
        codigoNegocio: 'Negocio1',
        mensaje: "Excelente sitio, muy buena atención",
        respuesta: "",
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Comentario'
    },{
        _id: 'Comentario2',
        fecha: new Date(2024, 3, 18, 15, 30, 45),
        calificacion: 5,
        codigoCliente: 'Cliente2',
        codigoNegocio: 'Negocio2',
        mensaje: "Excelente sitio, muy buena atención",
        respuesta: "",
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Comentario'
    },{
        _id: 'Comentario3',
        fecha: new Date(2024, 3, 18, 15, 30, 45),
        calificacion: 5,
        codigoCliente: 'Cliente3',
        codigoNegocio: 'Negocio3',
        mensaje: "Excelente sitio, muy buena atención",
        respuesta: "",
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Comentario'
    },{
        _id: 'Comentario4',
        fecha: new Date(2024, 3, 18, 15, 30, 45),
        calificacion: 5,
        codigoCliente: 'Cliente4',
        codigoNegocio: 'Negocio4',
        mensaje: "Excelente sitio, muy buena atención",
        respuesta: "",
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Comentario'
    },{
        _id: 'Comentario5',
        fecha: new Date(2024, 3, 18, 15, 30, 45),
        calificacion: 5,
        codigoCliente: 'Cliente5',
        codigoNegocio: 'Negocio5',
        mensaje: "Excelente sitio, muy buena atención",
        respuesta: "",
        _class: 'co.org.uniquindio.unilocal.modelo.documentos.Comentario'
    }
]);

