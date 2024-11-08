import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { useNavigate } from 'react-router-dom';
import {Container} from "@mui/material";
import  StoreIcon  from "@mui/icons-material/Storefront";
import {Divider} from "@mui/material";

export default function ButtonAppBar() {

    const navigate = useNavigate();

    const logoutUser = () => {
        //logout()
        //navigate("/login")
        alert("This feature is not available yet")
    }

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Container maxWidth="xl">
                    <Toolbar disableGutters>
                        <StoreIcon />
                        <Divider sx={{margin:"10px"}} orientation="vertical" variant="middle" flexItem />
                        <Typography
                            variant="h6"
                            noWrap
                            component="a"
                            sx={{
                                mr: 2,
                                display: { xs: 'none', md: 'flex' },
                                fontFamily: 'monospace',
                                fontWeight: 700,
                                letterSpacing: '.3rem',
                                color: 'inherit',
                                textDecoration: 'none',
                            }}
                        >
                            EGL - Store
                        </Typography>
                        <Divider sx={{margin:"10px"}} orientation="vertical" variant="middle" flexItem />
                        <Button variant={'contained'} color={'secondary'} href={'/'} sx={{marginX: '10px'}}>Productos</Button>
                        <Button variant={'contained'} color={'success'} href={'/ventas'}>Ventas</Button>
                        <Typography variant="h4" component="div" sx={{ flexGrow: 1 }}>

                        </Typography>
                        <Button variant={'contained'} onClick={logoutUser} color="error" sx={{marginX: "10px"}}>Salir</Button>
                        <Button variant={'outlined'} color={'inherit'} >Registrarse</Button>
                    </Toolbar>
                </Container>
            </AppBar>
        </Box>
    );
}