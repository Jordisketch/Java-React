import React, { Component } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "./Index.scss"

class Index extends Component {


    constructor() {
        super();
        this.state = {
            clientes: [],
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/api/cliente")
            .then((response) => {
                const clientes = response.data;
                this.setState({ clientes });

            });
    }

    render() {
        const { clientes } = this.state;

        return (
            <div className= "listClientes">
                <Link to = {{pathname : "./insertar"}}> Insertar</Link>
                <ul>
                    { clientes.length > 0 ?(
                        clientes.map((cliente) => (
                            <li className = "cliente" key= {cliente.id}>
                                <Link to = {{ pathname : `${cliente.id}` }}>
                                    { cliente.nombre}
                                </Link>

                            </li>
                        

                        ))
                    ):( 
                        <li>No existen clientes</li>
                    )}

                </ul>

            </div>
        );

    }


}

export default Index;