import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "./Create.scss";

class Create extends Component {
    constructor() {
        super();
        this.state = {
            cedula: 0,
            nombre: "",
            apellido: "",
            direccion: "",
            telefono: 0,
            correo: "",
            mensaje: "",
        }
    }

    componentDidMount() {

    }

    changeContent = (event) => {
        let input = event.target.name;
        let value = event.target.value;

        this.setState({ [input]: value });
    }

    submit = (event) => {
        event.preventDefault();

        const cliente = {
            cedula: this.state.cedula,
            nombre: this.state.nombre,
            apellido: this.state.apellido,
            direccion: this.state.direccion,
            telefono: this.state.telefono,
            correo: this.state.correo
        }

        axios.post("http://localhost:8080/api/cliente", null, { params: cliente })
            .then((response) => {
                console.log(response);
                const mensaje = response.data;
                this.setState({
                    cedula: 0,
                    nombre: "",
                    apellido: "",
                    direccion: "",
                    telefono: 0,
                    correo: ""
                });
                this.setState({ mensaje });
            })
            .catch((error) => {
                const mensaje = "Se produjo un error al conectarse a la base de datos";
                this.setState({ mensaje });
            })
    }

    render() {
        const { mensaje } = this.state;
        return (
            <div className="formulario">
                <form action="POST" onSubmit={this.submit}>

                    <label htmlFor="cedula">Cedula: </label>
                    <input type="number" id="cedula" name="cedula" required onChange={this.changeContent} />

                    <label htmlFor="nombre">Nombre: </label>
                    <input type="text" id="nombre" name="nombre" required onChange={this.changeContent} />

                    <label htmlFor="apellido">Apellido: </label>
                    <input type="text" id="Apellido" name="Apellido" required onChange={this.changeContent} />

                    <label htmlFor="direccion">Direccion: </label>
                    <input type="text" id="direccion" name="direccion" required onChange={this.changeContent} />

                    <label htmlFor="telefono">Telefono: </label>
                    <input type="number" id="calorias" name="calorias" required onChange={this.changeContent} />

                    <label htmlFor="correo">Correo: </label>
                    <input type="text" id="correo" name="correo" required onChange={this.changeContent} />


                    <input type="submit" className="boton" value="Insertar" />
                    <span className="mensaje">{mensaje}</span>
                </form>


                <Link to={{ pathname: "/" }}>Regresar</Link>
            </div>
        )
    }

}

export default Create;