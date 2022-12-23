import React from 'react'
import Link from 'next/link'
import { AiOutlineShopping,AiFillHome } from 'react-icons/ai'
import {Cart,User} from './';
import {useStateContext} from '../context/StateContext';

const Navbar = () => {
  const {showCart, setShowCart,totalQuantities,profileOpen,setProfileOpen} = useStateContext();
  console.log({showCart, setShowCart,totalQuantities,profileOpen,setProfileOpen});
  return (
    <div className = "navbar-container">
        <p className = "logo">
          <AiFillHome />
          <Link href = "/">  Home </Link>
        </p>
          <button type = "button" className = "cart-icon" onClick = {() => setShowCart(true)}>
            <AiOutlineShopping />
            <span className = 'cart-item-qty'>{totalQuantities}</span>
          </button>
        {showCart && <Cart />}
      <User/>
    </div>
  )
}
export default Navbar