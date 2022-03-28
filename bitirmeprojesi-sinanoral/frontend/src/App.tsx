import { Box, Text, VStack, Grid } from "@chakra-ui/react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ColorModeSwitcher } from "./components/ColorModeSwitcher";
import Home from "./pages/Home";
import Login from "./pages/Login";
import { QueryClient, QueryClientProvider } from "react-query";
import Signup from "./pages/Signup";

const queryClient = new QueryClient();

export const App = () => (
  <QueryClientProvider client={queryClient}>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route
          path="*"
          element={
            <main style={{ padding: "1rem" }}>
              <p>There's nothing here!</p>
            </main>
          }
        />
      </Routes>
    </BrowserRouter>
  </QueryClientProvider>
);
