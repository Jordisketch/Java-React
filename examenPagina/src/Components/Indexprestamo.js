import React, { Component } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "./Indexprestamo.scss"

class Indexprestamo extends Component {


    constructor() {
        super();
        this.state = {
            prestamos: [],
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/api/prestamo")
            .then((response) => {
                const prestamos = response.data;
                this.setState({ prestamos });

            });
    }

    render() {
        const { prestamos } = this.state;

        return (
            <div className= "listprestamos">
                <Link to = {{pathname : "./insertarprestamo"}}> Insertar</Link>
                <ul>
                    { prestamos.length > 0 ?(
                        prestamos.map((prestamo) => (
                            <li className = "prestamo" key= {prestamo.id}>
                                <Link to = {{ pathname : `${prestamo.id}` }}>
                                    { prestamo.id_cliente}
                                </Link>

                            </li>
                        

                        ))
                    ):( 
                        <li>No existen prestamos</li>
                    )}

                </ul>

            </div>
        );

    }


}

export default Indexprestamo;