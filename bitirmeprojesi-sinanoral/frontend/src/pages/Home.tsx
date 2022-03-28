import { Flex } from "@chakra-ui/react";
import CreateProduct from "../components/products/CreateProduct";
import ProductTable from "../components/products/ProductTable";

export default function Home() {
  return (
    <Flex justify="space-between" w="100%">
      <Flex flexDir={"column"} w="45%">
        <CreateProduct />
      </Flex>
      <ProductTable />
    </Flex>
  );
}
