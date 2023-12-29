import { BrowserRouter, Route, Routes } from 'react-router-dom'
import GuildList from './pages/GuildList'
import Home from './pages/Home'

export default function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/guild" element={<GuildList />} />
      </Routes>
    </BrowserRouter>
  )
}
