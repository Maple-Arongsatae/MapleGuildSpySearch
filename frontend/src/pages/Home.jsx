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
  const server = '리부트'
  const [guildNameInput, setGuildNameInput] = useState(['']) // 초기에 하나의 입력창을 가진 배열을 설정

  // 새로운 입력창을 추가하는 함수
  const addGuildNameInput = () => {
    if (guildNameInput.length < 10) {
      setGuildNameInput([...guildNameInput, ''])
    }
  }

  // 입력창의 값이 변경될 때 호출되는 함수
  const handleGuildNameInputChange = (index, value) => {
    const newGuildNameInput = [...guildNameInput]
    newGuildNameInput[index] = value
    setGuildNameInput(newGuildNameInput)
  }

  // 입력창을 삭제하는 함수
  const removeGuildNameInput = index => {
    console.log(guildNameInput.length)
    if (guildNameInput.length > 1) {
      const newGuildNameInput = [...guildNameInput]
      newGuildNameInput.splice(index, 1)
      setGuildNameInput(newGuildNameInput)
    }
  }

  // const [guildName, setGuildName] = useState('')
  // const onChange = ({ target }) => setGuildName(target.value)

  const clickMove = () => {
    navigate('/guild', {
      state: {
        server: server,
        guilds: ['새빨간', '새까만', '새파란', '샛노란'],
      },
    })
  }

  return (
    <>
      <div className="flex flex-col items-center">
        <div className="w-72">
          <Select label="Server">
            <Option>스카니아</Option>
            <Option>베라</Option>
            <Option>루나</Option>
            <Option>제니스</Option>
            <Option>크로아</Option>
            <Option>유니온</Option>
            <Option>엘리시움</Option>
            <Option>이노시스</Option>
            <Option>레드</Option>
            <Option>오로라</Option>
            <Option>아케인</Option>
            <Option>노바</Option>
            <Option>리부트</Option>
            <Option>리부트2</Option>
          </Select>
        </div>
        {guildNameInput.map((guildNameInput, index) => (
          <div className="relative flex w-full max-w-[24rem]">
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
            />
            <Button
              size="sm"
              color="red"
              className="!absolute right-1 top-1 rounded"
              onClick={() => removeGuildNameInput(index)}
            >
              <FaTrashAlt />
            </Button>
          </div>
        ))}
        <div>
          <Button color="amber" onClick={addGuildNameInput}>
            <FaPlus />
          </Button>
        </div>
        <div>
          <Button onClick={() => clickMove()}>
            <FaSearch />
          </Button>
        </div>
      </div>
    </>
  )
}
