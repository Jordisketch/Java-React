import React, { Component } from "react";
import { Redirect, Link } from "react-router-dom";
import axios from "axios";

class Show extends Component {
	constructor(props) {
		super(props);
		this.state = {
			id: props.match.params.id,
			cliente: {},
			mensaje: "",
			redirect: false,
		};
	}

	componentDidMount() {
		axios
			.get("http://localhost:8080/api/cliente/" + this.state.id)
			.then((response) => {
				if (response.status === 200) {
					const cliente = response.data;
					this.setState({ cliente });
				} else {
					this.setState({ cliente: null, mensaje: response.data });
				}
			});
	}

	delete = () => {
		axios
			.delete("http://localhost:8080/api/cliente/" + this.state.id)
			.then((response) => {
				if (response.status === 200) {
					this.setState({ redirect: true });
				}
			});
	};

	render() {
		const {cliente, mensaje, redirect } = this.state;
		return (
			<div>
				{cliente != null ? (
					<div className="detalles">
						<h1>ID: {cliente.id}</h1>
                        <h1>Cedula: {cliente.cedula}</h1>
						<h3>Nombre: {cliente.nombre}</h3>
						<h3>Apellido: {cliente.apellido}</h3>
						<h3>Direccion: {cliente.direccion}</h3>
						<h3>Telefono: {cliente.telefono}</h3>
                        <h1>Correo: {cliente.correo}</h1>

						<Link to={{ pathname: `/modificar/${cliente.id}` }}>
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

export default Show;
