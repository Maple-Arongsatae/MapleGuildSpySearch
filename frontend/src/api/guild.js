import axios from 'axios'

export const fetchData = async (server, guilds) => {
  const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy'
  const URL = `${PROXY}/maple/guild/spy`
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
