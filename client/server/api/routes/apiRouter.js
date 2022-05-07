const express = require('express');
const apiRouter = express.Router();

const { exec } = require('child_process');
const { request } = require('undici');
var robot = require("robotjs");

const apiVersion = '/v0';

/**
 * Environment
 */
const port = process.env.PORT;
const deckEndpoint = process.env.DECK_ENDPOINT;

const database = require('../../db/inMemoryDb');

apiRouter.get(`${apiVersion}/login/:hash`, async (req, res) => {
    const { hash } = req.params;
    const { body } = await request(`${deckEndpoint}/${hash}`);
    const shortcuts = await body.json();
    database.insertAll(shortcuts);
    res.status(200).json(shortcuts);
});

apiRouter.get(`${apiVersion}/shortcut/:id`, (req, res) => {
    const { id } = req.params;
    const shortcut = database.findById(id);
    if (shortcut) {
        console.table(shortcut);
        exec(`wmctrl -x -a ${shortcut.app}`);
        robot.keyTap(shortcut.key, shortcut.modifiers);
        res.status(200);
    } else {
        res.status(404).json({ error: "Shortcut not found" });
    }
});

apiRouter.get(`/test/:hash`, (req, res) => {
    const { hash } = req.params;
    const shortcuts = [
        {
            id: 0,
            key: 'm',
            app: 'Discord',
            name: 'Mute',
            modifiers: ['control', 'shift']
        },
        {
            id: 1,
            key: 'm',
            app: 'Teams',
            name: 'Mute',
            modifiers: ['control', 'shift']
        }
    ];
    if (hash === "abc") {
        res.status(200).json(shortcuts);
    } else {
        res.status(403).json({ error: "Invalid hash" });
    }
});

module.exports = apiRouter;