import sanityClient from '@sanity/client'
import imageUrBuilder from '@sanity/image-url'
export const client = sanityClient({
    projectId: 'clfhhp86',
    dataset: 'production',
    apiVersion:'2022-11-29',
    useCdn: true,
    token: process.env.NEXT_PUBLIC_SANITY_TOKEN
});

const builder = imageUrBuilder(client)

export const urlFor = (source) => builder.image(source);
