import React from 'react'
import { Input } from '@material-tailwind/react'
import { Button } from '@material-tailwind/react'
import { FaSearch } from 'react-icons/fa'

const SearchBar = ({ onSubmit }) => {
  const handleSubmit = e => {
    e.preventDefault()
    onSubmit(e.target.elements.filter.value)
  }

  return (
    <form onSubmit={handleSubmit}>
      <div className="flex gap-x-1.5">
        <Input type="text" label="Character Name" name="filter" />
        <Button type="submit">
          <FaSearch />
        </Button>
      </div>
    </form>
  )
}

export default SearchBar
