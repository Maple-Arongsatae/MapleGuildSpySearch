import { useQuery } from '@tanstack/react-query'
import React, { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import { fetchData } from '../api/guild'
import GuildItem from '../components/GuildItem'
import { FaHome } from 'react-icons/fa'
import { RxTriangleDown } from 'react-icons/rx'
import SearchBar from '../components/SearchBar'
import { Spinner } from '@material-tailwind/react'
import { Button } from '@material-tailwind/react'
import { FaArrowUp } from 'react-icons/fa'

export default function GuildList() {
  const location = useLocation()
  const navigate = useNavigate()
  const server = location.state.server
  const guildNameInput = location.state.guilds

  const { data, isLoading, isError, error } = useQuery({
    queryKey: ['guildData', server, guildNameInput],
    queryFn: () => fetchData(server, guildNameInput),
  })

  if (isError) {
    return <div>{error}</div>
  }

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

  const [expandedGuilds, setExpandedGuilds] = useState({})

  const toggleGuild = guildName => {
    setExpandedGuilds(prevState => ({
      ...prevState,
      [guildName]: !prevState[guildName],
    }))
  }

  const handleScrollToTop = () => {
    window.scrollTo({ top: 0, behavior: 'smooth' })
  }

  if (isLoading) {
    return (
      <div className="flex flex-col gap-8 items-center mt-28">
        <Spinner className="h-12 w-12" />
        <p>조회 길드 당 약 40초의 데이터 조회 시간이 소요됩니다.</p>
      </div>
    )
  }

  return (
    data && (
      <>
        <div className="flex w-9/12 mx-auto my-5 items-center gap-x-2 justify-between">
          <div className="flex items-center gap-x-2">
            <FaHome
              className="text-lg cursor-pointer"
              onClick={() => navigate('/')}
            />
            <p className="font-semibold text-lg">서버 : {data.world}</p>
          </div>
          <div className="flex">
            <SearchBar onSubmit={handleSearchSubmit} />
          </div>
        </div>

        {data.guildIndex.map(guildName => {
          return (
            <div key={guildName}>
              <div className="flex w-9/12 mx-auto my-5 py-1 gap-x-2.5 items-center border-b border-gray-500">
                <p className="font-semibold text-lg">{guildName}</p>
                <RxTriangleDown
                  className={`text-xl cursor-pointer transition-transform ${
                    expandedGuilds[guildName] ? 'transform rotate-180' : ''
                  }`}
                  onClick={() => toggleGuild(guildName)}
                />
              </div>
              {expandedGuilds[guildName] && (
                <GuildItem guilds={filteredGuilds[guildName]} />
              )}
            </div>
          )
        })}
        <div className="flex w-max gap-4 ml-auto fixed right-14 bottom-2.5">
          <Button
            color="amber"
            className="border rounded-full"
            onClick={handleScrollToTop}
          >
            <FaArrowUp className="cursor-pointe" />
          </Button>
        </div>
      </>
    )
  )
}
