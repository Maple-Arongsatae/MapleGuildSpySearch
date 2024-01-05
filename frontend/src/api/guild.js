import axios from 'axios'

export const fetchData = async (server, guilds) => {
  const URL = `/maple/guild/spy`
  const response = await axios.post(
    URL,
    {
      world: server,
      guilds: guilds,
    },
    { headers: { 'Content-Type': 'application/json' } }
  )

  return response.data
}
