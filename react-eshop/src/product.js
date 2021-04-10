import { useState } from "react";

function Product({ product, onClickHandler }) {

  const [isInCart, setIsInCart] = useState(false)


  return (<div style={{
    width: "150px",
    border: "1px solid black",
    display: "flex",
    flexDirection: "column",
    margin: "5px",
    padding: "5px"
  }}>
    <h2>{product.name}</h2>
    <div>{product.price} CZK</div>
    <div><img src={product.image} height={150} width={150}/></div>
    <div>{isInCart && "In cart"}</div>
    <button onClick={() => {
      setIsInCart(true);
      onClickHandler(product)
    }}>Buy</button>
  </div>)
}

export default Product;