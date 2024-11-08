import React, { useEffect, useState } from 'react'
import { Box,
    IconButton, Button, Dialog, DialogTitle, DialogContent, DialogActions, TextField,
    Snackbar,
    Alert,
    Card}
    from '@mui/material'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import TablePagination from '@mui/material/TablePagination';

import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import SaveIcon from '@mui/icons-material/Save';
import { Link } from 'react-router-dom';
import axiosInstance from '../axiosConfig';

export default function Products() {
    const [categories, setCategories] = useState([])
    const [allSubCategories, setAllSubCategories] = useState([])
    const [filteredSubCategories, setFilteredSubCategories] = useState([])

    const [snackbarOpen, setSnackbarOpen] = useState(false)
    const [snackbarMessage, setSnackbarMessage] = useState("")
    const [snackbarSeverity, setSnackbarSeverity] = useState('success')

    const [filterText, setFilterText] = useState("")

    const [page, setPage] = useState(0);  // Current page index
    const [rowsPerPage, setRowsPerPage] = useState(5);  // Number of rows per page

    const [open, setOpen] = useState(false)
    const [openEdit, setOpenEdit] = useState(false)
    const [products, setProducts] = useState([])
    const [deleteId, setDeleteId] = useState(null)
    const [confirmOpen, setConfirmOpen] = useState(false)
    const [newProduct, setNewProduct] = useState({
        nombre: '',
        costo: '',
        stock: ''
    })

    const [editProduct, setEditProduct] = useState({
        id: '',
        nombre: '',
        costo: '',
        stock: ''
    })

    const handleClickOpenEdit = (product) => {
        setEditProduct(product)
        setOpenEdit(true)
    }

    const handleChange = (e) => {
        setNewProduct({...newProduct, [e.target.name]: e.target.value});
    }

    const handleChangeEdit = (e) => {
        setEditProduct({...editProduct, [e.target.name]: e.target.value})
    }

    const handleConfirmOpen = (id) => {
        setDeleteId(id)
        setConfirmOpen(true)
    }
    const handleConfirmClose = (id) => {
        setDeleteId(null)
        setConfirmOpen(false)
    }

    const handleClickOpen = () => {
        setOpen(true)
    }

    const handleClose = () => {
        setOpen(false)
    }

    const handleCloseEdit = () => {
        setOpenEdit(false)
    }

    const handleSnackbarClose = () => {
        setSnackbarOpen(false)
    }

    const handleDelete = async (id)  => {
        setSnackbarOpen(true)
        try {
            await axiosInstance.delete(`http://localhost:8080/productos/delete/${id}`);
            setProducts(products.filter(product => product.id !== id));

            setSnackbarMessage("Product was deleted successfully!")
            setSnackbarSeverity("success")

            handleConfirmClose()
        } catch (error) {
            console.log('Error occured deleting the product: ', error);
            setSnackbarMessage("An error occured trying to delete a product.")
            setSnackbarSeverity("warning")
        }
    };

    const handleEditProduct = async () => {
        setSnackbarOpen(true)
        try {
            const response = await axiosInstance.put(`http://localhost:8080/productos/update`, {
                ...editProduct
            });
            setProducts(products.map(product =>
                product.id === editProduct.id ? response.data : product
            ));
            setSnackbarMessage("Product was updated successfully!")
            setSnackbarSeverity("success")
            handleCloseEdit();
        } catch (error) {
            setSnackbarMessage("There was an error updating the product! Please try again")
            setSnackbarSeverity("warning")
            console.log('Error occured updating the product', error)
        }
    }

    const handleAddProduct = async () => {
        setSnackbarOpen(true)
        try {
            const response = await axiosInstance.post('http://localhost:8080/productos/create', {
                ...newProduct
            });
            const response_getall = await axiosInstance.get('http://localhost:8080/productos/all');
            setProducts([...products, response_getall.data])
            setNewProduct({
                nombre: '',
                costo: '',
                stock: ''
            });
            setSnackbarMessage("Product was added successfully!")
            setSnackbarSeverity("success")
            handleClose();
        }catch(error){
            setSnackbarMessage("There was an error adding a product! Please try again")
            setSnackbarSeverity("warning")
            console.log('There was an error adding the product!', error)
        }
    }

    const handleFilterChange = (event) => {
        setFilterText(event.target.value)
        setPage(0)
    }

    const filteredProducts  = products.filter( product =>
        (product.nombre && product.nombre.toLowerCase().includes(filterText.toLocaleLowerCase()))
    );

    useEffect(() => {
        axiosInstance.get('http://localhost:8080/productos/all').then(response => {
            setProducts(response.data)
        }).catch((error) => {
            console.log("There was an error fetching the products", error)
        })
    }, []);

    // Handle page change
    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    // Handle change in rows per page
    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);  // Reset the table to the first page whenever rows per page changes
    };

    const handleCategoryChange = (event) => {
        const selectedCategoryId = event.target.value
        setNewProduct({
            ...newProduct,
            categoryid: selectedCategoryId,
            subcategoryid: ''
        });

        const filteredSubCategories = allSubCategories.filter(
            (subCategory) => subCategory.categoryid === Number(selectedCategoryId)
        );
        setFilteredSubCategories(filteredSubCategories)
    }

    const handleSubCategoryChange = (event) => {
        const selectedSubCategoryId = event.target.value
        setNewProduct({
            ...newProduct,
            subcategoryid: selectedSubCategoryId
        })
    }


    return (
        <Box
            display="flex"
            justifyContent="center"
            alignItems="center"
            height="20%"
            padding="2rem"
            border="ActiveBorder"
        >
            <Card sx={{ width: '80%', padding: '2rem', border: '2px solid black', borderRadius: '8px' }}>
                <TableContainer>
                    <Box display="flex" justifyContent="flex-start">
                        <Button variant='contained' onClick={handleClickOpen}>Agregar Nuevo</Button>
                    </Box>

                    <Box display="flex" justifyContent="flex-end" >
                        <TextField
                            label="Buscar Productos"
                            variant="outlined"
                            value={filterText}
                            onChange={handleFilterChange}
                        >

                        </TextField>
                    </Box>
                    <hr></hr>
                    <Table aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell sx={{ fontWeight: 'bold' }} scope="col">ID</TableCell>
                                <TableCell sx={{ fontWeight: 'bold' }} scope="col">Nombre</TableCell>
                                <TableCell sx={{ fontWeight: 'bold' }} scope="col">Costo</TableCell>
                                <TableCell sx={{ fontWeight: 'bold' }} scope='col'>Stock</TableCell>
                                <TableCell sx={{ fontWeight: 'bold' }} scope="col" align={'center'} >Acciones</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            { filteredProducts !== null? filteredProducts.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((product, index) => (
                                <TableRow
                                    key={product.id}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                >
                                    <TableCell sx={{ fontSize: '1.1rem' }} scope="row">{page * rowsPerPage + index + 1}</TableCell>

                                    <TableCell sx={{ fontSize: '1.1rem' }}>{product.nombre}</TableCell>

                                    <TableCell sx={{ fontSize: '1.1rem' }}>$ {product.costo}</TableCell>
                                    <TableCell sx={{ fontSize: '1.1rem' }}>{product.stock}</TableCell>
                                    <TableCell align='center'>
                                        <IconButton color='error' onClick={() => handleConfirmOpen(product.id)}>
                                            <DeleteIcon />
                                        </IconButton>
                                        <IconButton color='primary' onClick={() => handleClickOpenEdit(product)}>
                                            <EditIcon />
                                        </IconButton>
                                    </TableCell>
                                </TableRow>
                            ) ): (<TableRow><TableCell>Loading... </TableCell></TableRow>)}
                        </TableBody>
                    </Table>
                    <TablePagination  sx={{ fontSize: '1.1rem' }}
                                      component="div"
                                      count={products!= null? products.length: 0}
                                      page={page}
                                      onPageChange={handleChangePage}
                                      rowsPerPage={rowsPerPage}
                                      onRowsPerPageChange={handleChangeRowsPerPage}
                                      rowsPerPageOptions={[5, 10, 25]}  // Options for rows per page
                    />
                </TableContainer>
                <hr></hr>
            </Card>
            {/* Confirmation Dialog for Deletion */}
            <Dialog open={confirmOpen}
                    style={{ width: '600px', maxWidth: '600px' }} // Custom width
                    onClose={handleConfirmClose}>
                <DialogTitle>Confirmar</DialogTitle>
                <DialogContent>
                    Estás seguro de borrar este producto?
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleConfirmClose} color="primary">
                        Cancelar
                    </Button>
                    <Button
                        onClick={() => {
                            handleDelete(deleteId);
                        }}
                        color="error"
                        variant="contained"
                        startIcon={<DeleteIcon/>}
                    >
                        Borrar
                    </Button>
                </DialogActions>
            </Dialog>

            {/* Modal Dialog for Adding New Product */}
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Nuevo Producto</DialogTitle>
                <DialogContent>
                    <TextField
                        margin="dense"
                        name="nombre"
                        label="Nombre"
                        type="text"
                        fullWidth
                        value={newProduct.nombre}
                        onChange={handleChange}
                    />
                    <TextField
                        margin="dense"
                        name="costo"
                        label="Costo"
                        type="number"
                        fullWidth
                        value={newProduct.costo}
                        onChange={handleChange}
                    />
                    <TextField
                        margin="dense"
                        name="stock"
                        label="Stock"
                        type="number"
                        fullWidth
                        value={newProduct.stock}
                        onChange={handleChange}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Cancelar
                    </Button>
                    <Button onClick={handleAddProduct} color="primary" variant="contained" startIcon={<SaveIcon />}>
                        Guardar
                    </Button>
                </DialogActions>
            </Dialog>

            {/* Modal Dialog for Editing Product */}
            <Dialog open={openEdit} onClose={handleCloseEdit}>
                <DialogTitle>Modificar Producto</DialogTitle>
                <DialogContent>
                    <TextField
                        margin="dense"
                        name="nombre"
                        label="Nombre"
                        type="text"
                        fullWidth
                        value={editProduct.nombre}
                        onChange={handleChangeEdit}
                    />
                    <TextField
                        margin="dense"
                        name="costo"
                        label="Costo"
                        type="number"
                        fullWidth
                        value={editProduct.costo}
                        onChange={handleChangeEdit}
                    />
                    <TextField
                        margin="dense"
                        name="stock"
                        label="Stock"
                        type="number"
                        fullWidth
                        value={editProduct.stock}
                        onChange={handleChangeEdit}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseEdit} color="primary">
                        Cancelar
                    </Button>
                    <Button color="primary" variant="contained"  onClick={handleEditProduct} startIcon={<SaveIcon/>} >
                        Guardar
                    </Button>
                </DialogActions>
            </Dialog>
            <Snackbar
                open={snackbarOpen}
                autoHideDuration={4000}
                onClose={handleSnackbarClose}
                anchorOrigin={{vertical: 'top', horizontal: 'right'}}
            >
                <Alert
                    onClose={handleSnackbarClose}
                    severity={snackbarSeverity}
                    sx={{
                        display: 'flex',
                        alignItems: 'center',
                        minHeight: '80px'
                    }}
                >
                    {snackbarMessage}
                </Alert>
            </Snackbar>
        </Box>
    )
}