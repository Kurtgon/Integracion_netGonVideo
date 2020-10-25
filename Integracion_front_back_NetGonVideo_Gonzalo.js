/* Utiliza el proyecto con Spring que has creado en el módulo de DWES para acceder consumirlo desde el frontend.

Desde el Frontend deberás crear un botón que realice una petición POST hacia el endpoint correspondiente de tu backend.

Deberás pasarle los valores necesarios (recomendado crear un formulario para enviar estos valores)

Crea otro botón que haga una petición GET al endpoint de tu proyecto backend para poder listar alguno de los datos de
tu proyecto.

Crea otro botón que haga una petición GET al endpoint de tu proyecto backend pasándole un ID para poder consultar
un registro concreto. Los datos obtenidos se cargarán en los campos del formulario existente.

Deberá entregarse enlace a github donde se encuentre el proyecto completo (frontend y backend) y subirlo también 
Moodle. El código debe tener sus correspondientes comentarios y un Readme en el que se explique las configuraciones
necesarias a realizar para montar el proyecto completo. */

//Nostraemos los botónes Post, Get y Lista
const buttonPost = document.getElementById("crearPost");
const buttonGet = document.getElementById("consultaGet");
const buttonList = document.getElementById("consultaList");

//Método GET
//Creamos el evento del botón
buttonList.addEventListener('click', (e) => {
    e.preventDefault();
    eliminarTabla();
    limpiarCampos();
    //Nos traemos los datos del cliente
    fetch('http://localhost:8080/api/customers')
        .then(respuesta => respuesta.ok ? Promise.resolve(respuesta) : Promise.reject(respuesta))
        .then(respuesta => respuesta.json())
        .then(respuesta => {
            //Creamos la tabla donde vamos a mostrar los datos
            let tabla = document.createElement("table");
            tabla.id="resultados";
            //Creamos la fila
            let filaCabecera = document.createElement("tr");
            //Creamos los campos de la cabecera clase Customer
            let cabeceraId = document.createElement("th");
            cabeceraId.appendChild(document.createTextNode("Id"));
            let cabeceraName = document.createElement("th");
            cabeceraName.appendChild(document.createTextNode("Name"));
            let cabeceraSurnames = document.createElement("th");
            cabeceraSurnames.appendChild(document.createTextNode("Surnames"));
            let cabeceraBirthDate = document.createElement("th");
            cabeceraBirthDate.appendChild(document.createTextNode("BirthDate"));
            let cabeceraDni = document.createElement("th");
            cabeceraDni.appendChild(document.createTextNode("Dni"));
            //Añadimos la celda a la fila
            filaCabecera.appendChild(cabeceraId);
            filaCabecera.appendChild(cabeceraName);
            filaCabecera.appendChild(cabeceraSurnames);
            filaCabecera.appendChild(cabeceraBirthDate);
            filaCabecera.appendChild(cabeceraDni);
            //Añadimos la fila a la tabla
            tabla.appendChild(filaCabecera);
            respuesta.forEach(datosCustomer => {
                //Creamos las filas para los campos de la clase Customer
                let fila = document.createElement("tr")
                let id = document.createElement("td");
                id.appendChild(document.createTextNode(datosCustomer.id));
                let name = document.createElement("td");
                name.appendChild(document.createTextNode(datosCustomer.name));
                let surnames = document.createElement("td");
                surnames.appendChild(document.createTextNode(datosCustomer.surnames));
                let birthDate = document.createElement("td");
                birthDate.appendChild(document.createTextNode(datosCustomer.birthDate));
                let dni = document.createElement("td");
                dni.appendChild(document.createTextNode(datosCustomer.dni));
                //Añadimos los campos a la fila
                fila.appendChild(id);
                fila.appendChild(name);
                fila.appendChild(surnames);
                fila.appendChild(birthDate);
                fila.appendChild(dni);
                //Añadimos la fila a la tabla
                tabla.appendChild(fila);
            })
            //Añadimos la tabla al html
            let divTabla = document.getElementById("tabla");
            divTabla.appendChild(tabla);
        })
        .catch(error => alert("Se ha producido un error"));
    })

    //Método GET con Id
    //Creamos el evento del botón
    buttonGet.addEventListener('click', (e) => {
        e.preventDefault();
        eliminarTabla();
        //Variable id que la obtenemos del valor que introduzca en el input
        let id = document.getElementById("id").value;
        //Controlamos que introduzca un valor en el campo id
        if(id == ""){
            //Indicamos una alerta para que sea visible al usuario
            alert("Error, el campo id no puede estar vacío");
        }else{
            fetch(`http://localhost:8080/api/customers/${id}`)
            .then(respuesta => respuesta.ok ? Promise.resolve(respuesta) : Promise.reject(respuesta))
            .then(respuesta => respuesta.json())
            .then(respuesta => {
                document.getElementById("name").value=respuesta.name;
                document.getElementById("surnames").value=respuesta.surnames;
                document.getElementById("birthdate").value=respuesta.birthDate;
                document.getElementById("dni").value=respuesta.dni;
                //Limpiamos el campo id
                document.getElementById("id").value="";
            })
            //Controlamos el id si no existe
            .catch(error =>{
                alert(`Error, no existe ningún cliente con el id: ${id}`);
                //reseteamos el valor de id para que vuelva a introducir uno nuevo
                document.getElementById("id").value="";
                limpiarCampos();
            })
        }
    })

    //Método Post
    //Creamos el evento del botón
    buttonPost.addEventListener('click', (e) => {
        e.preventDefault();
        //Recogemos los elementos del formulario
        let name = document.getElementById("name").value;
        let surnames = document.getElementById("surnames").value;
        let birthDate = document.getElementById("birthdate").value;
        let dni = document.getElementById("dni").value;
        //Enviar datos método Post usando el API Fetch
        fetch(`http://localhost:8080/api/customers/`, {
            method:'POST',
            body: JSON.stringify({
                //Creamos el json con los campos y recogemos los datos del formulario para cada campo
                name: name,
                surnames: surnames,
                birthDate: birthDate,
                dni: dni
            }),
            headers: {
                "Content-type": "application/json"
            }
        })
        //Lanzamos la promesa
        .then(respuesta => respuesta.ok ? Promise.resolve(respuesta) : Promise.reject(respuesta))
        .then(respuesta => {
            alert("El cliente se ha añadido");
            limpiarCampos();
        })
        .catch(error => {
            alert("Error no se ha podido añadir el cliente");
            limpiarCampos();
        })
    })

    //Función para limpiar los campos del formulario
    const limpiarCampos = () => {
        document.getElementById("name").value="";
        document.getElementById("surnames").value="";
        document.getElementById("birthdate").value="";
        document.getElementById("dni").value="";
    }

    //Función para eliminar la tabla
    const eliminarTabla = () => {
        let tabla = document.getElementById("resultados");
        if(tabla != null){
            tabla.parentNode.removeChild(tabla);
        }
    }

    //Botones Limpiar formulario y Ocultar tabla
    
    //Botón limpiar el formulario
    const buttonClear = document.getElementById("limpiarFormulario");
    //Creamos el evento click para limpiar el formulario
    buttonClear.addEventListener("click", (e) =>{
        e.preventDefault();
        limpiarCampos();
    })
    
    //Botón ocultar tabla
    const buttonHiden = document.getElementById("hiddenList");
    //Creamos el evento click para ocultar la tabla
    buttonHiden.addEventListener("click", (e) =>{
        e.preventDefault();
        ocultarTabla();
    })

    //Función ocultar tabla
    const ocultarTabla = () =>{
        let div = document.getElementById("resultados");
            div.style.display="none";

    }
