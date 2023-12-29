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
      <div className="flex flex-col items-center text-center w-9/12 border border-solid border-gray-400 rounded-lg bg-gray-400 my-14 mx-auto p-5">
        <p className="text-2xl font-semibold">이중길드 유저 찾기</p>
        본길드 및 부길드에 속해있는 길드원 중 이중길드 유저를 찾아냅니다. <br />
        <br />
        <p className="font-semibold">사용방법</p>
        서버를 선택하고 길드명을 작성합니다.
        <br />
        부길드의 경우 + 버튼을 눌러 작성합니다.
        <br />
        <br />
        길드명 작성이 완료되면 돋보기를 클릭해 주세요.
        <br />
        <br />
        <p className="text-red-500 font-semibold">
          최대 조회 가능 길드 수 : 10개
        </p>
        <br />
        <p className="font-semibold">made by seop/arong</p>
      </div>
      <div className="flex flex-col items-center">
        <div className="w-full max-w-[24rem] my-2.5">
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
        <div className="flex gap-x-1.5">
          <Button color="amber" size="lg" onClick={addGuildNameInput}>
            <FaPlus />
          </Button>
          <Button size="lg" onClick={() => clickMove()}>
            <FaSearch />
          </Button>
        </div>
      </div>
    </>
  )
}
