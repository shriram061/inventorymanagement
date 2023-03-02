import React, { Component } from "react";
import "../style.css";
import User from "../Service/UserService.js";
import { Link } from "react-router-dom";

import ViewUserComponent from "./ViewUserComponent";
// import Signin from "./Signin";
export default class Signup extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userName: "",
      emailId: "",
      password: "",
    };
    this.register = this.register.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }
  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    this.setState({
      [name]: value,
    });
  }

  register = (event) => {
    event.preventDefault();
    let user = {
      // emailId: this.state.emailId,
      userName: this.state.userName,
      password: this.state.password,
      emailId: this.state.emailId,
    };
    console.log(JSON.stringify(user));
    User.getUserByName(this.state.userName)
      .then((res) => {
        if (res.data == "") {
          User.saveUser(user)
            .then((res) => {
              console.log(res.data);
              alert("registration successful");
              window.location.reload(false);
              //window.location.href = "http://localhost:3000/login";
            })
            .catch((err) => {
              console.log(err);
            });
        }
      })
      .catch((err) => {
        alert(err);
      });
  };




  
  render() {
    return (
      <div id="op">
      
      <nav class="navbar navbar-dark bg-dark"><span>f</span>
      <a class="navbar-brand" href="#">INVENTORY-MANAGEMENT --- ADMIN-PANEL</a>
      <Link class="btn btn-outline-success my-2 my-sm-0" to="/" >Logout</Link>
  </nav>

     
        
<div className="main">
        <form className="form-main" onSubmit={this.register}>
          <h2>REGISTRATION</h2>
          <input
            type="text"
            className="field"
            name="userName"
            placeholder="Enter User Name"
            value={this.state.userName}
            onChange={this.handleChange.bind(this)}
          />
          <input
            type="email"
            className="field"
            name="emailId"
            placeholder="Enter E-mail Address"
            value={this.state.emailId}
            onChange={this.handleChange.bind(this)}
            required
          />
          <input
            type="password"
            className="field"
            name="password"
            placeholder="Enter Password"
            value={this.state.password}
            onChange={this.handleChange.bind(this)}
            required
          />
          <button type="submit" className="butr">
            SIGNUP
          </button>
        </form><div className="text-center left-main p-2">
        <h2 id="z">FLOW </h2>

        
        {/* <ViewLoanComponent/> */}

        <div class="row">
 
    <div class="card">
      <div class="card-body">
        <h5 id="rq" class="card-title">View User Details</h5>
        <p  id="rq" class="card-text">UserId | UserName | Password | Delete.</p>
        <Link to="/adm" class="btn btn-primary">View</Link>
      </div>
    
  </div>
  
</div>




        </div>
      </div></div>
    );
  }
}
