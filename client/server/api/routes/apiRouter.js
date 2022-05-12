const express = require('express');
const apiRouter = express.Router();
const status = require('http-status-codes');

const { exec } = require('child_process');
const { request } = require('undici');
var robot = require("robotjs");

const apiVersion = '/v0';

/**
 * Environment
 */
const deckEndpoint = process.env.DECK_ENDPOINT;

const database = require('../../db/inMemoryDb');

apiRouter.get(`${apiVersion}/login/:hash`, async (req, res) => {
    const { hash } = req.params;
    const { body } = await request(`${deckEndpoint}/${hash}`);
    const shortcuts = await body.json();
    database.insertAll(shortcuts);
    res.status(status.StatusCodes.OK).json(shortcuts);
});

apiRouter.get(`${apiVersion}/shortcut/:id`, (req, res) => {
    const { id } = req.params;
    const shortcut = database.findById(id);
    if (shortcut) {
        exec(`wmctrl -x -a ${shortcut.app}`);
        robot.keyTap(shortcut.key, shortcut.modifiers);
        res.status(status.StatusCodes.OK).json({ message: "Shortcut performed" });
    } else {
        res.status(status.StatusCodes.NOT_FOUND).json({ error: "Shortcut not found" });
    }
});

apiRouter.get('*', express.static('dist/client-ui'));

module.exports = apiRouter;