// import '../styles/globals.css'
// import type { AppProps } from 'next/app'
// export default function App({ Component, pageProps }: AppProps) {
//   return <Component {...pageProps} />
// }
// export default App
import React from 'react'
import { Layout } from '../components'
import '../styles/globals.css'
import { StateContext } from '../context/StateContext'
import { Toaster } from 'react-hot-toast';

function MyApp({ Component, pageProps }) {
  return (
      <StateContext>
        <Layout>
          <Toaster />
          <Component{...pageProps} />
        </Layout>
      </StateContext>
  )
}
export default MyApp