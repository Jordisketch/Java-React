import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "./Edit.scss"
import { render } from "react-dom";


class Edit extends Component{

    constructor(props){
        super(props);
        this.state = {
            id: props.match.params.id,
            cliente : {},
            mensaje : "",
        };
    }



    componentDidMount(){
        axios
        .get("http://localhost:8080/api/cliente" + this.state.id)
        .then((response) => {
            const cliente = response.data;
            this.setState({cliente});
        })

    }

    changeContent = (event) => {
        let input = event.target.name;
        let data = event.target.value;

        let cliente = this.state.cliente;


        switch (input){

            case "cedula":
                cliente.cedula = data;
                break;
            
            case "nombre":
                 cliente.nombre = data;
                 break;
            
            case "apellido" :
                cliente.apellido = data;
                break;
            
            case "direccion":
                cliente.direccion = data;
                break;
            
            case "telefono":
                cliente.telefono = data;
                break;
            
            case "correo":
                cliente.correo = data;
                break;
         default:
                
            


        }
        this.setState({cliente});
    };

    submit = (event) => {
        event.preventDefault();

        const { cliente } = this.state;

        axios
        .patch("http://localhost:8080/api/cliente" + cliente.id,
        null,
        { params : cliente} 
        )
        .then((response) => {
            console.log(response);
            const mensaje = response.data;
            this.setState({ mensaje });
            
        });
    }

    render() {
        const { mensaje, cliente} =this.state;

        return(

            <div className = "formulario">
                {cliente != null ?(
                    <form onSubmit ={this.submit}>
                           <label htmlFor="cedula">Cedula: </label>
                            <input type="number" id="cedula" name="cedula" required defaultValue={cliente.cedula} onChange={this.changeContent} />

                            <label htmlFor="nombre">Nombre: </label>
                            <input type="text" id="nombre" name="nombre" required defaultValue={cliente.nombre} onChange={this.changeContent} />

                            <label htmlFor="apellido">Apellido: </label>
                            <input type="text" id="apellido" name="apellido" defaultValue={cliente.apellido} required onChange={this.changeContent} />

                            <label htmlFor="direccion">Direccion: </label>
                            <input type="text" id="direccion" name="direccion" required defaultValue={cliente.direccion} onChange={this.changeContent} />

                            <label htmlFor="telefono">Telefono: </label>
                            <input type="number" id="telefono" name="telefono" defaultValue={cliente.telefono} required onChange={this.changeContent} />

                            <label htmlFor="correo">Correo: </label>
                            <input type="text" id="correo" name="correo" required defaultValue={cliente.correo} onChange={this.changeContent} />

                            
                            <input type="submit" className="boton" value="Modificar" />
                            <span className="mensaje">{mensaje}</span>
                    </form>

                ):  ( 
                    <div className = "mensaje">
                        <p>El cliente no existe</p>
                    </div>
                )}
                    <Link to={{ pathname: `/${cliente.id}` }}>Regresar</Link>
            </div>

            );
        
        }
    
    }


export default Edit;