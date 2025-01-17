export interface UserDTO {
    id?: number;
    name: string;
    email: string;
    password?: string | null;
    userCode?: string | null;
    status ?: string | null;
    role: string;
    createAdmin?: UserDTO | null;
  }