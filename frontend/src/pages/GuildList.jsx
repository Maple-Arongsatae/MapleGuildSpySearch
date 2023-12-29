import { useQuery } from '@tanstack/react-query'
import React from 'react'
import { useLocation } from 'react-router-dom'
import { fetchData } from '../api/guild'
import GuildItem from '../components/GuildItem'

export default function GuildList() {
  const location = useLocation()
  console.log(location.state.server)
  const server = location.state.server
  const guildNameInput = location.state.guilds

  const { data, isLoading, isError } = useQuery({
    queryKey: ['guildData', server, guildNameInput],
    queryFn: () => fetchData(server, guildNameInput),
  })

  console.log(data)
  return (
    data && (
      <>
        <GuildItem
          world={data.world}
          guildIndex={data.guildIndex}
          guilds={data.guilds}
        />
      </>
    )
  )
}
