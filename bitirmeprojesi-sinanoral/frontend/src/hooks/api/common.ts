export interface ErrorResponse {
  date: Date;
  detailMessage: string;
  message: string;
  status: number;
  success: boolean;
}

export interface SuccessResponse<T> {
  data: T;
  responseDate: Date;
  message: string | null;
  success: boolean;
}

export enum HttpVerb {
  GET = "GET",
  POST = "POST",
  PUT = "PUT",
  PATCH = "PATCH",
  DELETE = "DELETE",
}

export function getToken() {
  return localStorage.getItem("token")?.split(" ")[1];
}
