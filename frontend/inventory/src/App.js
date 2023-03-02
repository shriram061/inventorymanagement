
import React, { Component } from "react";
import { BrowserRouter as Router, Route, Routes} from "react-router-dom";
import ViewUserComponent from "./Component/ViewUserComponent";
import ViewDetails from "./Component/ViewDetails";
import 'bootstrap/dist/css/bootstrap.min.css'; 
import AddComponent from "./Component/AddComponent";
import Signin from "./Component/Signin";
import Signup from "./Component/Signup";
import Home from "./Component/Home";
import Admin from "./Component/Admin";
import "./style.css"
import EditService1 from "./Component/EditService1";
import About from "./Component/About";
import Search1 from "./Component/Search1";



class App extends Component {
  render() {
    return (
      <Router>
            <Routes>
            <Route path="/" exact element={<Signin/>} />
            <Route path="/register" element={<Signup/>} />
            <Route path="/login" element={<Signin/>} />
            <Route path="/onlyviewstatus" element={<ViewDetails/>} />
            <Route path="/home" element={<Home/>} />
            <Route path="/add" element={<AddComponent/>} />
            <Route path="/adm"  element={<ViewUserComponent/>} />
            <Route path="/about" element={<About/>}/>
            <Route path="/edit/:id" element={<EditService1/>}/>
            
           
            <Route path="/viewstatus" element={<Search1/>}/>

            <Route path="/admin" element={<Admin/>}/>
            </Routes>
      </Router>
    );
  }
}
export default App;

