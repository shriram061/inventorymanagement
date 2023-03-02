import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import "./search.css";


export default function Search1() {
  const [data, setData] = useState([]);

    const [products, setProducts] = useState([]);
    const [keyword, setKeyword] = useState('');
  
    useEffect(() => {
      loadProducts();
    }, [keyword]);
  
    function loadProducts() {
      fetch('http://localhost:9876/search?keyword=', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ keyword: keyword })
      })
      .then(response => response.json())
      .then(data => {
        const filteredProducts = data.filter(product => {
          return product.productName.toLowerCase().includes(keyword.toLowerCase());
        });
        setProducts(filteredProducts);
      })
      .catch(error => {
        console.error(error);
      });
    }
    

    const handleDelete = (id) => {
      fetch(`http://localhost:9876/delete/${id}`, {
        method: 'DELETE',
  
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error('Failed to delete item');
          }
          setData((prevData) => prevData.filter((item) => item.id !== id));
        })
        .catch((error) => {
          console.error(error);
        });
      window.location.reload(false);
    };


  
    function handleSearch(event) {
      event.preventDefault();
      loadProducts();
    }
  
    return (
      <div>
        <div className="bg-secondary nav-bar nav-sticky navbar-expand-md navbar-dark bg-dark">
          <nav className="navbar navbar-expand-md navbar-dark navsection shadow">
            <div class="nav navbar-nav mx-auto navbar-left">
              <Link to="/home">  <a class="navbar-brand mx-auto nav navbar-nav navbar-right brand" >INVENTORY MANAGEMENT</a></Link>
            </div>
            <div class="nav navbar-nav mx-auto navbar">

            </div>
            <div class="nav mx-auto navbar-nav navbar-right order-3">
              <ul class="navbar-nav ml-auto">
                <Link to="/add">   <a className="navbtn "  >Add</a></Link>
                <Link to="/onlyviewstatus">   <a className="navbtn "  >Report</a></Link>

                <Link to="/login">    <a className="navbtn "> Logout</a></Link>
              </ul>
            </div>
          </nav>
        </div>
        <div className="container">
        <div className="shadow  mt-4 p-5 bg-white rounded text-dark">
        <form onSubmit={handleSearch}>
        <div className="form-group d-flex justify-content-center">
          
          <input 
    type="text" 
    value={keyword} 
    onChange={e => setKeyword(e.target.value)} 
    placeholder="Enter a search term..."
    className="form-control mr-2"
  />
  <button type="submit" className="btn btn-primary">Search</button>
          </div>
        </form>
        <hr/>
             <h1 className="text-center">Product Details</h1><hr />
           
        
        <table class="table table-striped table-light table-bordered">
          <thead>
            <tr>
              <th>Product Id</th>
              <th>Product Name</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Status</th>
              <th>Validity</th>
              <th>Delete</th>
              <th>Edit</th>

            </tr>
          </thead>
          <tbody>
            {products.filter(product =>{
              return product.productName.toLowerCase().includes(keyword.toLowerCase()); 
            }).map(product=>
              <tr key={product.productId}>
                <td>{product.productId}</td>
                <td>{product.productName}</td>
                <td>{product.price}</td>
                <td>{product.quantity}</td>
                <td><span className={product.status === "INSTOCK" ? "badge bg-success" :product.status === "LOWSTOCK" ? "badge bg-warning" : "badge bg-danger"}>{product.status}</span></td>
                <td>{product.validity}</td>
                <td><button id="z" onClick={() => handleDelete(product.productId)}>Delete</button></td>
                
                <td>
                        <Link to={`/edit/${product.productId}`}> Edit </Link>

                      </td>
                
              </tr>
            )}
          </tbody>
        </table></div>
      </div></div>
    );
    }