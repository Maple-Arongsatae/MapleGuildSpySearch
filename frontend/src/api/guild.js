import axios from 'axios'

export const fetchData = async (server, guilds) => {
  const response = await axios.post(
    '/maple/guild/spy',
    {
      world: server,
      guilds: guilds,
    },
    { headers: { 'Content-Type': 'application/json' } }
  )

  return response.data
}
