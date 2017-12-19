'use strict';

const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {users: []};
    }

    loadUsersFromServer(pageSize) {
        var self = this;
        $.ajax({
            url: "http://localhost:8080/api/users"
        }).then(function (data) {
            self.setState({users: data._embedded.users});
        });
    }

    componentDidMount() {
        this.loadUsersFromServer(this.state.pageSize);
    }

    render() {
        return (
            <div className="container">
                <h1>Users</h1>
                <UserTable users={this.state.users}/>
            </div>);
    }
}

class UserTable extends React.Component {
    render() {
        var users = this.props.users.map(user =>
            <User key={user._links.self.href} user={user}/>
        );
        return (
            <table className="table table-striped">
                <tbody>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Description</th>
                    <th>Delete</th>
                </tr>
                {users}
                </tbody>
            </table>
        )
    }
}

class User extends React.Component {
    constructor(props) {
        super(props);
        this.state = {display: true};
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete() {
        var self = this; //?????????????????????????????????????        WHAT
        $.ajax({
            url: this.props.user._links.self.href,
            type: "GET",
            success(result) {
                self.setState({display: false});
                toastr.success("Success");
            },
            error(xhr, ajaxOptions, thrownError) {
                toastr.error(xhr.responseJSON.message);
            }
        });
    };

    render() {
        if (this.state.display == false)
            return null;
        else
            return (
                <tr>
                    <td>{this.props.user.name}</td>
                    <td>{this.props.user.lastName}</td>
                    <td>{this.props.user.email}</td>
                    <td>
                        <button className="btn btn-danger" onClick={this.handleDelete}>Delete</button>
                    </td>
                </tr>
            )
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('react')
)