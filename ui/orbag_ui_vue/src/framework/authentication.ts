
export type LoginRequest = {
    user_name: String
    password: String
    persistent: Boolean
}

export type LoginResponse = {
    token: String
}

