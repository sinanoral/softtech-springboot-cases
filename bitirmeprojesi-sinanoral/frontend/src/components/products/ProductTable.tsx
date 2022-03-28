import { EditIcon, DeleteIcon } from "@chakra-ui/icons";
import {
  Table,
  TableCaption,
  Thead,
  Tr,
  Th,
  Tbody,
  Td,
  Flex,
  IconButton,
} from "@chakra-ui/react";
import { api } from "../../hooks";
import { Product } from "../../hooks/api/products/product";

export default function ProductTable() {
  const { isLoading, isError, data: products, error } = api.useGetAllProducts();
  const { mutate: deleteProductById } = api.useDeleteProductById();

  if (isLoading) {
    return <span>Loading...</span>;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Table variant="simple">
      <TableCaption>Products</TableCaption>
      <Thead>
        <Tr>
          <Th>Name</Th>
          <Th>Category</Th>
          <Th isNumeric>Price</Th>
          <Th isNumeric>Vat amount</Th>
          <Th isNumeric>Vat inclusive price</Th>
          <Th isNumeric>Update</Th>
          <Th isNumeric>Delete</Th>
        </Tr>
      </Thead>
      <Tbody>
        {products?.data.map((product: Product) => (
          <Tr>
            <Td>{product.name}</Td>
            <Th>{product.categoryId}</Th>
            <Th isNumeric>{product.price}</Th>
            <Th isNumeric>{product.vatAmount}</Th>
            <Th isNumeric>{product.vatInclusivePrice}</Th>
            <Th>
              <Flex justify="flex-end">
                <IconButton
                  colorScheme="orange"
                  aria-label="Update product"
                  icon={<EditIcon />}
                />
              </Flex>
            </Th>
            <Th>
              <Flex justify="flex-end">
                <IconButton
                  colorScheme="red"
                  aria-label="Delete product"
                  icon={<DeleteIcon />}
                  onClick={() => {
                    deleteProductById(product.id);
                  }}
                />
              </Flex>
            </Th>
          </Tr>
        ))}
      </Tbody>
    </Table>
  );
}
