import { useGetAllCategories } from "./categories/useGetAllCategories";
import { useCreateProduct } from "./products/useCreateProduct";
import { useGetAllProductsByCategoryId } from "./products/useGetProductsByCategoryId";
import { useLogin } from "./auth/useLogin";
import { useRegister } from "./auth/useRegister";
import { useDeleteProductById } from "./products/useDeleteProductById";
import { useGetAllProducts } from "./products/useGetAllProducts";
import { useGetProductById } from "./products/useGetProductById";
import { useUpdateProductById } from "./products/useUpdateProductById";

export default {
  useLogin,
  useRegister,
  useGetAllProducts,
  useGetProductById,
  useUpdateProductById,
  useDeleteProductById,
  useGetAllProductsByCategoryId,
  useCreateProduct,
  useGetAllCategories,
};
