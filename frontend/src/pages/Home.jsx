import React from 'react'
import { Button } from '@material-tailwind/react'
import { Select, Option } from '@material-tailwind/react'
import { Input } from '@material-tailwind/react'
import { FaPlus } from 'react-icons/fa'
import { FaSearch } from 'react-icons/fa'
import { FaTrashAlt } from 'react-icons/fa'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

export default function Home() {
  const navigate = useNavigate()
  const [selectedServer, setSelectedServer] = useState('')
  const [guildNameInput, setGuildNameInput] = useState([''])

  const handleServerChange = value => {
    setSelectedServer(value)
  }

  const addGuildNameInput = () => {
    if (guildNameInput.length < 10) {
      setGuildNameInput([...guildNameInput, ''])
    }
  }

  const handleGuildNameInputChange = (index, value) => {
    const newGuildNameInput = [...guildNameInput]
    newGuildNameInput[index] = value
    setGuildNameInput(newGuildNameInput)
  }

  const removeGuildNameInput = index => {
    if (guildNameInput.length > 1) {
      const newGuildNameInput = [...guildNameInput]
      newGuildNameInput.splice(index, 1)
      setGuildNameInput(newGuildNameInput)
    }
  }

  const clickMove = () => {
    navigate('/guild', {
      state: {
        server: selectedServer,
        guilds: guildNameInput.filter(name => name.trim() !== ''),
      },
    })
  }

  return (
    <>
      <div className="flex flex-col items-center text-center w-9/12 bg-opacity-50 border-none rounded-lg bg-gray-200 mt-14 mb-5 mx-auto p-3 w-full leading-tight">
        <p className="text-lg font-semibold">이중길드 유저 찾기</p>
        <p className="text-sm">
          본길드 및 부길드에 속해있는 길드원 중 이중길드 유저를 찾아냅니다.{' '}
          <br />
        </p>
        <br />
        <br />
        <p className="font-semibold">사용방법</p>
        <p className="text-sm">서버를 선택하고 길드명을 작성합니다.</p>
        <p className="text-sm">부길드의 경우 + 버튼을 눌러 작성합니다.</p>
        <p className="text-sm">
          길드명 작성이 완료되면 돋보기를 클릭해 주세요.
        </p>
        <br />
        <br />
        <p className="text-red-500 text-sm font-semibold">
          최대 조회 가능 길드 수 : 10개
        </p>
        <br />
        <p className="text-sm">made by. seop / arong</p>
      </div>

      <div className="flex flex-col items-center">
        <div className="w-full max-w-[24rem] my-2.5">
          <Select
            id="server"
            label="Server"
            value={selectedServer}
            onChange={value => handleServerChange(value)}
          >
            <Option value="스카니아">스카니아</Option>
            <Option value="베라">베라</Option>
            <Option value="루나">루나</Option>
            <Option value="제니스">제니스</Option>
            <Option value="크로아">크로아</Option>
            <Option value="유니온">유니온</Option>
            <Option value="엘리시움">엘리시움</Option>
            <Option value="이노시스">이노시스</Option>
            <Option value="레드">레드</Option>
            <Option value="오로라">오로라</Option>
            <Option value="아케인">아케인</Option>
            <Option value="노바">노바</Option>
            <Option value="리부트">리부트</Option>
            <Option value="리부트2">리부트2</Option>
          </Select>
        </div>
        {guildNameInput.map((guildNameInput, index) => (
          <div className="relative flex w-full max-w-[24rem] my-2.5">
            <Input
              key={index}
              type="text"
              label="Guild Name"
              value={guildNameInput}
              onChange={e => handleGuildNameInputChange(index, e.target.value)}
              className="pr-20"
              containerProps={{
                className: 'min-w-0',
              }}
              icon={
                <FaTrashAlt
                  className="cursor-pointer"
                  onClick={() => removeGuildNameInput(index)}
                />
              }
            />
          </div>
        ))}
        <div className="flex gap-x-1.5">
          <Button
            color="amber"
            size="lg"
            onClick={addGuildNameInput}
            disabled={guildNameInput.length === 10}
          >
            <FaPlus />
          </Button>
          <Button
            size="lg"
            onClick={() => clickMove()}
            disabled={
              !selectedServer || guildNameInput.some(name => name.trim() === '')
            }
          >
            <FaSearch />
          </Button>
        </div>
      </div>
    </>
  )
}
