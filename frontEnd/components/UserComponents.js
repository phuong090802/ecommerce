import React from 'react';
import UserService from '../pages/api/UserService';
class UserComponent extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            users:[]
        }
    }
    componentDidMount(){
        UserService.getUsers().then((response) => {
            this.setState({ users: response.data})
        });
    }
    render (){
        return (
            <div>
                <h1 className = "text-center"> Users List</h1>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                            <td> User Id</td>
                            <td> User Name</td>
                            <td> Password</td>
                            <td> User Email</td>
                            <td> Role</td>
                            <td> OTP</td>
                            <td> EXP_OTP</td>
                        </tr>

                    </thead>
                    <tbody>
                        {
                            this.state.users.map(
                                user => 
                                <tr key = {user.id}>
                                     <td> {user.username}</td>   
                                     <td> {user.password}</td>   
                                     <td> {user.email}</td>
                                     <td> {user.role}</td>
                                     <td> {user.otp}</td>   
                                     <td> {user.exp_otp}</td>   
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>

        )
    }
}
export default UserComponent