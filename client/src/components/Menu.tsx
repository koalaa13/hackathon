import React, { useState } from "react";
import { AppBar, Box, Button, CssBaseline, Divider, Drawer, List, ListItem, ListItemButton, ListItemIcon, ListItemText, SwipeableDrawer, Toolbar, Typography } from '@mui/material';

function Menu() {
    const drawerWidth = 30;

    return (
        <Box sx={{ display: 'flex' }}>
            <CssBaseline />
            <Drawer
                sx={{
                    width: `${drawerWidth}%`,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': {
                        width: `${drawerWidth}%`,
                        boxSizing: 'border-box',
                    },
                }}
                variant="permanent"
                anchor="left"
                
            >
                <List>
                    {['Search', 'Load', 'Time', 'Queue', 'Tasks'].map((text, index) => (
                        <ListItem key={text} disablePadding>
                            <ListItemButton>
                                
                                <ListItemText primary={text} />
                            </ListItemButton>
                        </ListItem>
                    ))}
                </List>
            </Drawer>
        </Box>
    );
}

export default Menu;