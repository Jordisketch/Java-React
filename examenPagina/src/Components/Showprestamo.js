import React, { Component } from "react";
import { Redirect, Link } from "react-router-dom";
import axios from "axios";

class Showprestamo extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: props.match.params.id,
            prestamo: {},
            mensaje: "",
            redirect: false,
        };
    }

    componentDidMount() {
        axios
            .get("http://localhost:8080/api/prestamo/" + this.state.id)
            .then((response) => {
                if (response.status === 200) {
                    const prestamo = response.data;
                    this.setState({ prestamo });
                } else {
                    this.setState({ prestamo: null, mensaje: response.data });
                }
            });
    }

    delete = () => {
        axios
            .delete("http://localhost:8080/api/prestamo/" + this.state.id)
            .then((response) => {
                if (response.status === 200) {
                    this.setState({ redirect: true });
                }
            });
    };

    render() {
        const { prestamo, mensaje, redirect } = this.state;
        return (
            <div>
                {prestamo != null ? (
                    <div className="detalles">
                        <h1>Id: {prestamo.id}</h1>
                        <h1>Id_cliente: {prestamo.id_cliente}</h1>
                        <h3>Fecha_inicio: {prestamo.fecha_inicio}</h3>
                        <h3>Fecha_fin: {prestamo.fecha_fin}</h3>
                        <h3>Monto_total: {prestamo.monto_total}</h3>
                        <h3>Descripcion: {prestamo.descrpcion}</h3>

                        <Link to={{ pathname: `/modificarprestamo/${prestamo.id}` }}>
                            Modificar
						</Link>
                        <button onClick={this.delete}>Eliminar</button>
                    </div>
                ) : (
                    <div className="mensaje">
                        <p>{mensaje}</p>
                    </div>
                )}

                <Link to={{ pathname: "/" }}>Regresar</Link>
                {redirect && <Redirect to={"/"} />}
            </div>
        );
    }
}

export default Showprestamo;
