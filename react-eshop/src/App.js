import './App.css';
import Product from "./product";
import { useState, useEffect } from "react";
import ProductForm from "./product-form";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

function App() {

  const [cart, setCart] = useState([])
  const [data, setData] = useState([])
  const [isPending, setIsPending] = useState(true)
  const [error, setError] = useState()

  const onNewProductHandler = (product) => {
    const newData = [...data];
    newData.push(product);
    console.log(newData);
    setData(newData)
  }

  useEffect(()=> {
    setTimeout(()=> {
      fetch('http://localhost:3001/products')
        .then(response => {
          if (response.ok) {
          return response.json()
          }
          throw new Error(`Unable to get data: ${response.statusText}`)
        })
        .then(json => setData(json))
        .catch((err) => setError(err.message))
        .finally(()=> setIsPending(false))
      }
    , 1000)

  },[])

  const addToCartHandler = function(product) {
    const newCart = [...cart];
    newCart.push(product);
    console.log(newCart);
    setCart(newCart)
  }

  const removeFromCartHandler = function(product) {
    const newCart = [...cart];
    const productIndex = newCart.findIndex(item=> item.id === product.id)
    newCart.splice(productIndex, 1)
    setCart(newCart)
  }


  return (
    <Router>
    <div className="App">

      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/edit-product">Edit product</Link>
          </li>
          <li>
            <Link to="/cart">Cart</Link>
          </li>
        </ul>
      </nav>


      <Switch>
        <Route path="/cart">
          <h1>Shopping cart</h1>
          {cart.map(item=> <div>{item.name} <button onClick={() => removeFromCartHandler(item)}>-</button></div>)}
        </Route>
        <Route path="/edit-product">
          <ProductForm onNewProduct={onNewProductHandler}/>
        </Route>
        <Route path="/">
          {<div>{cart.length}</div>}
          {isPending && "Loading data..."}
          {error && <div>{error}</div>}

          <div style={{
            display: "flex",
            flexWrap: "wrap",
            margin: "5px"
          }}>
            {data.map(item => <Product key={item.id} product={item} onClickHandler={addToCartHandler}/>)}
          </div>
        </Route>
      </Switch>
    </div>
    </Router>
  );
}



export default App;
