import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
import React from 'react'
import Router from './Router'

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      networkMode: 'always',
    },
    mutations: {
      networkMode: 'always',
    },
  },
})

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <Router />
      <ReactQueryDevtools initialIsOpen={false} />
    </QueryClientProvider>
  )
}

export default App
