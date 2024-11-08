import logo from '../../logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes } from "react-router-dom";
import ButtonAppBar from "../Appbar";
import Products from "../Products";
import Ventas from "../Ventas";

function App() {
  return (

    <div className="App">
      <BrowserRouter>
          <ButtonAppBar></ButtonAppBar>
          <Routes>
              <Route path={''} element={<Products></Products>} />
              <Route path={'/ventas'} element={<Ventas></Ventas>} />
          </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
