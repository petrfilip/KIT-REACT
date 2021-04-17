import { useState } from "react";

function ProductForm(props) {

  const [productName, setProductName] = useState("")
  const [price, setPrice] = useState(0)
  const [image, setImage] = useState("")

  const [feedback, setFeedback] = useState()




  const onSubmitHandler = event => {
    event.preventDefault();

    const newProduct = {
      name: productName,
      price: price,
      image
    }


    fetch('http://localhost:3001/products', {
      method: 'POST', // *GET, POST, PUT, DELETE, etc.
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newProduct) // body data type must match "Content-Type" header
    }).then(r=> r.json())
      .then(json => setFeedback(json))
      .finally(()=> {
        props.onNewProduct(newProduct);
        setProductName("");
        setPrice(0);
        setImage("")
      });


  }


  return (
    <>
    <form onSubmit={onSubmitHandler}>
      <input type={"text"} value={productName} placeholder={"Name"} onChange={(e) => setProductName(e.target.value)}/>
      <input type={"text"} value={image} placeholder={"Image"} onChange={(e) => setImage(e.target.value)}/>
      <input type={"number"} value={price} placeholder={"Price"} onChange={(e) => setPrice(e.target.value)}/>
      <input type={"submit"}/>
    </form>

      {feedback && <div>{JSON.stringify(feedback)}</div>}

    </>
  )
}

export default ProductForm;