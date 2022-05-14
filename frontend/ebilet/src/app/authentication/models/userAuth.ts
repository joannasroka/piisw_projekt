import { AccountStatus } from "./accountStatus";
import { UserRole } from "./userRole";

export interface UserAuth {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  accountStatus: AccountStatus;
  role: UserRole;
}
