import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "./Editprestamo.scss"



class Editprestamo extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: props.match.params.id,
            prestamo: {},
            mensaje: "",
        };
    }



    componentDidMount() {
        axios
            .get("http://localhost:8080/api/prestamo" + this.state.id)
            .then((response) => {
                const prestamo = response.data;
                this.setState({ prestamo });
            })

    }

    changeContent = (event) => {
        let input = event.target.name;
        let data = event.target.value;

        let prestamo = this.state.prestamo;


        switch (input) {

            case "id_cliente":
                prestamo.id_cliente = data;
                break;

            case "fecha_inicio":
                prestamo.fecha_inicio = data;
                break;

            case "fecha_fin":
                prestamo.fecha_fin = data;
                break;

            case "monto_total":
                prestamo.monto_total = data;
                break;

            case "descripcion":
                prestamo.descripcion = data;
                break;

            default:




        }
        this.setState({ prestamo });
    };

    submit = (event) => {
        event.preventDefault();

        const { prestamo } = this.state;

        axios
            .patch("http://localhost:8080/api/prestamo" + prestamo.id,
                null,
                { params: prestamo }
            )
            .then((response) => {

                const mensaje = response.data;
                this.setState({ mensaje });

            });
    }

    render() {
        const { mensaje, prestamo } = this.state;

        return (

            <div className="formulario">
                {prestamo != null ? (
                    <form onSubmit={this.submit}>
                        <label htmlFor="id_cliente">id_cliente: </label>
                        <input type="number" id="id_cliente" name="id_cliente" required defaultValue={prestamo.id_cliente} onChange={this.changeContent} />

                        <label htmlFor="fecha_inicio">Fecha_inicio: </label>
                        <input type="text" id="fecha_inicio" name="fecha_inicio" required defaultValue={prestamo.fecha_inicio} onChange={this.changeContent} />

                        <label htmlFor="fecha_fin">Fecha_fin: </label>
                        <input type="text" id="fecha_fin" name="fecha_fin" defaultValue={prestamo.fecha_fin} required onChange={this.changeContent} />

                        <label htmlFor="monto_total">Monto_total: </label>
                        <input type="number" id="monto_total" name="monto_total" defaultValue={prestamo.monto_total} required onChange={this.changeContent} />

                        <label htmlFor="descripcion">Descripcion: </label>
                        <input type="text" id="descripcion" name="descripcion" required defaultValue={prestamo.descripcion} onChange={this.changeContent} />


                        <input type="submit" className="boton" value="Modificar" />
                        <span className="mensaje">{mensaje}</span>
                    </form>

                ) : (
                    <div className="mensaje">
                        <p>El prestamo no existe</p>
                    </div>
                )}
                <Link to={{ pathname: `/${prestamo.id}` }}>Regresar</Link>
            </div>

        );

    }

}


export default Editprestamo;