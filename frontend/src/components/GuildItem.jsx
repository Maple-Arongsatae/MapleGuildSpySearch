import React from 'react'
import { Card, Typography } from '@material-tailwind/react'

export default function GuildItem({ guilds }) {
  const TABLE_HEAD = ['길드원', '소속길드', '본캐릭', '본캐릭 길드']

  return (
    <>
      <Card className="h-full w-9/12 overflow-scroll mx-auto">
        <table className="w-full min-w-max table-auto text-left">
          <thead>
            <tr>
              {TABLE_HEAD.map(head => (
                <th
                  key={head}
                  className="border-b border-blue-gray-100 bg-blue-gray-50 p-4"
                >
                  <Typography
                    variant="small"
                    color="blue-gray"
                    className="font-normal leading-none opacity-70"
                  >
                    {head}
                  </Typography>
                </th>
              ))}
            </tr>
          </thead>
          <tbody>
            {guilds.map(
              (
                {
                  nickname,
                  guild,
                  mainCharacterNickname,
                  mainCharacterGuild,
                  spy,
                },
                index
              ) => (
                <tr
                  key={index}
                  className={
                    mainCharacterGuild === '확인필요'
                      ? 'bg-yellow-300'
                      : 'even:bg-blue-gray-50/50' && spy
                      ? 'bg-red-500 bg-opacity-50'
                      : 'even:bg-blue-gray-50/50'
                  }
                >
                  <td className="p-4">
                    <Typography
                      variant="small"
                      color="blue-gray"
                      className="font-normal"
                    >
                        <a href={`https://maple.gg/u/${nickname}`} target="_blank" rel="noopener noreferrer">{nickname}</a>
                    </Typography>
                  </td>
                  <td className="p-4">
                    <Typography
                      variant="small"
                      color="blue-gray"
                      className="font-normal"
                    >
                      {guild}
                    </Typography>
                  </td>
                  <td className="p-4">
                    {mainCharacterNickname === "확인필요" ? (
                      <Typography variant="small" color="blue-gray" className="font-normal">
                        {mainCharacterNickname}
                      </Typography>
                    ) : (
                      <Typography variant="small" color="blue-gray" className="font-normal">
                        <a href={`https://maple.gg/u/${mainCharacterNickname}`} target="_blank" rel="noopener noreferrer">
                          {mainCharacterNickname}
                        </a>
                      </Typography>
                    )}
                  </td>
                  <td className="p-4">
                    <Typography
                      variant="small"
                      color="blue-gray"
                      className="font-normal"
                    >
                      {mainCharacterGuild}
                    </Typography>
                  </td>
                </tr>
              )
            )}
          </tbody>
        </table>
      </Card>
    </>
  )
}
