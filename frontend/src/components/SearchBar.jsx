import React, { useState } from 'react'
import { Input } from '@material-tailwind/react'
import { Button } from '@material-tailwind/react'
import { FaSearch } from 'react-icons/fa'
import { IoMdRefresh } from 'react-icons/io'

const SearchBar = ({ onSubmit }) => {
  const [filterValue, setFilterValue] = useState('')

  const handleSubmit = e => {
    e.preventDefault()
    // onSubmit(e.target.elements.filter.value)
    onSubmit(filterValue)
  }

  const handleReset = e => {
    setFilterValue('')
    onSubmit('')
  }

  return (
    <form onSubmit={handleSubmit}>
      <div className="flex gap-x-1.5">
        <Input
          type="text"
          label="Character Name"
          name="filter"
          value={filterValue}
          onChange={e => setFilterValue(e.target.value)}
          icon={
            <IoMdRefresh className="cursor-pointer" onClick={handleReset} />
          }
        />
        <Button type="submit">
          <FaSearch />
        </Button>
      </div>
    </form>
  )
}

export default SearchBar
