import React,{useState} from "react";
import {IoSettingsSharp} from "react-icons/io5";
import {BsBagCheck} from "react-icons/bs";
import {GrHelp} from "react-icons/gr";
import {BiLogOut} from "react-icons/bi";
import { IoSettingsOutline } from "react-icons/io5";
import  Link  from "next/link"
import { AiOutlineHeart } from "react-icons/ai";

const User = () =>
{
  const user = true
  const [profileOpen, setProfileOpen] = useState(false)

  const close = () => {
    setProfileOpen(null)
  }
  return (
    <>
      <div className='profile'>
        {user ? (
          <>
            <button className = "img" onClick = {() =>setProfileOpen(!profileOpen)}>
              <img src='https://cdn-icons-png.flaticon.com/512/2202/2202112.png' alt='' width="30" height = "30"/>
            </button>
            {profileOpen && (
              <div className="openProfile boxItems" onClick={close}>
                <div className = "image">
                  <div className = "img">
                    <img src='https://cdn-icons-png.flaticon.com/512/2202/2202112.png' alt='' width="30" height = "30"/>
                  </div>
                  <div className = "text">
                    <h4>Milo Huy</h4>
                    <label htmlFor="">VietNam</label>
                  </div>
                </div>
                <Link href = "./components/UserComponents">
                  <button className="box">
                    <IoSettingsOutline className = 'icon' />
                    <h4>My Account</h4>
                  </button>
                </Link>
                <button className="box">
                  <BsBagCheck className = 'icon' />
                  <h4>My Order</h4>
                </button>
                <button className="box">
                  <AiOutlineHeart className = 'icon' />
                  <h4>Wishlist</h4>
                </button>
                <button className="box">
                  <GrHelp className = 'icon' />
                  <h4>Help</h4>
                </button>
                <button className="box">
                  <BiLogOut className = 'icon' />
                  <h4>Log out</h4>
                </button>
              </div>
            )}
          </>
        ):(
          <button>My account</button>
        )}
        </div>
        </>
  )
}
export default User


    