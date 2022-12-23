import React from 'react';
import Link from 'next/link';
import { urlFor } from '../lib/client'
const MiloHuyBanner = ({milohuyBanner}) => {
  return (
    <div className="hero-banner-container">
        <div>
            <p className ="beats-solo">{milohuyBanner.smallText}</p>
            <h2>{milohuyBanner.midText}</h2>
            <h1>{milohuyBanner.largeText1}</h1>
            <img src={urlFor(milohuyBanner.image)} alt ="headphones" className="hero-banner-image"/>
            <div>
                <Link href = {"/product/${milohuyBanner.product"}>
                    <button type="button">{milohuyBanner.buttonText}</button>
                </Link>
                <div className ="desc">
                    <h5>Description</h5>
                    <p>{milohuyBanner.desc}</p>
                </div>
            </div>
        </div>
    </div>
  )
}

export default MiloHuyBanner