import { useQuery } from '@tanstack/react-query'
import React, { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import { fetchData } from '../api/guild'
import GuildItem from '../components/GuildItem'
import { FaHome } from 'react-icons/fa'
import { TbTriangleInvertedFilled } from 'react-icons/tb'
import SearchBar from '../components/SearchBar'

export default function GuildList() {
  const location = useLocation()
  const navigate = useNavigate()
  console.log(location.state.server)
  const server = location.state.server
  const guildNameInput = location.state.guilds

  const { data, isLoading, isError } = useQuery({
    queryKey: ['guildData', server, guildNameInput],
    queryFn: () => fetchData(server, guildNameInput),
  })

  const [searchKeyword, setSearchKeyword] = useState('')
  const handleSearchSubmit = keyword => {
    setSearchKeyword(keyword)
  }

  const filteredGuilds = {}
  if (data) {
      Object.keys(data.guilds).forEach(key => {
      filteredGuilds[key] = data.guilds[key].filter(item =>
        item.mainCharacterNickname.toLowerCase().includes(searchKeyword)
      )
    })
  }

  console.log(data)
  return (
    data && (
      <>
        <div className="flex w-9/12 justify-between mx-auto my-3 items-center">
          <p className="font-semibold text-lg">서버 : {data.world}</p>
          <FaHome
            className="text-xl cursor-pointer"
            onClick={() => navigate('/')}
          />
        </div>
        <SearchBar onSubmit={handleSearchSubmit} />
        {data.guildIndex.map(guildName => {
          return (
            <>
              <div className="flex w-9/12 mx-auto my-5 gap-x-2.5 items-center">
                <p className="font-semibold text-lg">{guildName}</p>
                <TbTriangleInvertedFilled className="text-lg cursor-pointer" />
              </div>
              <GuildItem
                world={data.world}
                guildIndex={data.guildIndex}
                // guilds={data.guilds[guildName]}
                guilds={filteredGuilds[guildName]}
              />
            </>
          )
        })}
      </>
    )
  )
}
