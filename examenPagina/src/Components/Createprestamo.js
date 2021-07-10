import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "./Createprestamo.scss";

class Createprestamo extends Component {
    constructor() {
        super();
        this.state = {
            id: 0,
            id_cliente: 0,
            fecha_inicio: "",
            fecha_fin: "",
            monto_total: 0,
            descripcion: "",
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

        const prestamo = {
            id: this.state.id,
            id_cliente: this.state.id_cliente,
            fecha_inicio: this.state.fecha_inicio,
            fecha_fin: this.state.fecha_fin,
            monto_total: this.state.monto_total,
            descripcion: this.state.descripcion
        }

        axios.post("http://localhost:8080/api/prestamo", null, { params: prestamo })
            .then((response) => {
                console.log(response);
                const mensaje = response.data;
                this.setState({

                    id: 0,
                    id_cliente: 0,
                    fecha_inicio: "",
                    fecha_fin: "",
                    monto_total: 0,
                    descripcion: "",

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
            <div className="formularioprestamo">
                <form action="POST" onSubmit={this.submit}>

                    <label htmlFor="id">Id: </label>
                    <input type="number" id="id" name="id" required onChange={this.changeContent} />

                    <label htmlFor="id_cliente">id_cliente: </label>
                    <input type="number" id="id_cliente" name="id_cliente" required onChange={this.changeContent} />


                    <label htmlFor="fecha_inicio">Fecha_inicio: </label>
                    <input type="text" id="fecha_inicio" name="fecha_inicio" required onChange={this.changeContent} />

                    <label htmlFor="fecha_fin">Fecha_fin: </label>
                    <input type="text" id="fecha_fin" name="fecha_fin" required onChange={this.changeContent} />

                    <label htmlFor="monto_total">Monto_total: </label>
                    <input type="number" id="calorias" name="calorias" required onChange={this.changeContent} />

                    <label htmlFor="descripcion">Descripcion: </label>
                    <input type="text" id="descripcion" name="descripcion" required onChange={this.changeContent} />


                    <input type="submit" className="boton" value="Insertar" />
                    <span className="mensaje">{mensaje}</span>
                </form>


                <Link to={{ pathname: "/" }}>Regresar</Link>
            </div>
        )
    }

}

export default Createprestamo;