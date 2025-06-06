import { LOGIN_ROUTE, BLOG_ROUTE, HOME_ROUTE, REGISTRATION_ROUTE } from "./utils/consts";
import Auth from "./pages/Auth"; 
import Home from "./pages/Home";
import Blog from "./pages/Blog";

export const authRoutes = [
    {
        path: LOGIN_ROUTE,
        component: Auth
    },
    {
        path: REGISTRATION_ROUTE,
        component: Auth
    },
    {
        path: HOME_ROUTE,
        component: Home
    }
]

export const publicRoutes = [
    {
        path: BLOG_ROUTE,
        component: Blog
    }
]