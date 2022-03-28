import {
  Button,
  Flex,
  FormControl,
  FormLabel,
  Heading,
  Input,
  Select,
} from "@chakra-ui/react";
import { useState } from "react";
import { api } from "../../hooks";
import { Category } from "../../hooks/api/categories/category";

export default function CreateProduct() {
  const { mutate } = api.useCreateProduct();
  const { data: categories } = api.useGetAllCategories();

  const [categoryId, setCategoryId] = useState<number>(0);
  const [name, setName] = useState<string>("");
  const [price, setPrice] = useState<number>(0);

  return (
    <Flex flexDir={"column"}>
      <Heading size={"xs"} color="green.400">
        Create Product
      </Heading>
      <FormControl isRequired>
        <FormLabel htmlFor="name">Product name</FormLabel>
        <Input
          id="name"
          placeholder="Pizza"
          onChange={(evt) => setName(evt.target.value)}
        />
      </FormControl>
      <FormControl isRequired>
        <FormLabel htmlFor="category">Category</FormLabel>
        <Select
          placeholder="Select category"
          onChange={(evt) => {
            setCategoryId(Number(evt.target.value));
          }}
        >
          {categories?.data.map((category: Category) => (
            <option value={category.id}>{category.name}</option>
          ))}
        </Select>
      </FormControl>
      <FormControl isRequired>
        <FormLabel htmlFor="price">Price</FormLabel>
        <Input
          id="price"
          placeholder="30"
          type={"number"}
          onChange={(evt) => setPrice(Number(evt.target.value))}
        />
      </FormControl>
      <Button
        borderRadius={0}
        variant="solid"
        colorScheme="green"
        width="full"
        mt={"10px"}
        onClick={() =>
          mutate({ name: name, categoryId: categoryId, price: price })
        }
      >
        Save
      </Button>
    </Flex>
  );
}
